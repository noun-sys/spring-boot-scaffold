package com.noun.sys.scaffold.base.config.datasource;

import com.github.pagehelper.PageInterceptor;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author:gaoxu
 * @create:2020-03-16 15:43
 **/
@Configuration
@MapperScan(basePackages = MysqlDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "SqlSessionTemplate")
public class MysqlDataSourceConfig {

    static final String PACKAGE = "com.bailian.mp.supplier.paas.repository.mapper";
    private static final String MAPPER_PATH = "classpath*:/mapper/*Mapper.xml";
    private static final String ENTITY_PACKAGE = "com.bailian.mp.supplier.paas.repository.entity";

    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;
    @Value("${spring.datasource.poolName}")
    private String poolName;
    @Value("${spring.datasource.maximumPoolSize}")
    private int maximumPoolSize;
    @Value("${spring.datasource.minimumIdle}")
    private int minimumIdle;
    @Value("${spring.datasource.connectionTimeout}")
    private int connectionTimeout;
    @Value("${spring.datasource.connectionTestQuery}")
    private String connectionTestQuery;
    @Value("${spring.datasource.idleTimeout}")
    private int idleTimeout;
    @Value("${spring.datasource.validationTimeout}")
    private int validationTimeout;
    @Value("${spring.datasource.maxLifetime}")
    private int maxLifetime;



    @Bean(name = "DataSource")
    @Primary
    @Qualifier("DataSource")
    public DataSource primaryDataSource() {
        Properties configProps = new Properties();
        configProps.put("jdbcUrl", url);
        configProps.put("username", username);
        configProps.put("password", password);
        configProps.put("connectionTestQuery", connectionTestQuery);
        configProps.put("driverClassName", driverClassName);
        configProps.put("poolName", poolName);
        configProps.put("maximumPoolSize", maximumPoolSize);
        configProps.put("minimumIdle", minimumIdle);
        configProps.put("connectionTimeout", connectionTimeout);
        configProps.put("idleTimeout", idleTimeout);
        configProps.put("validationTimeout", validationTimeout);
        configProps.put("dataSource.Properties", configProps);

        HikariConfig hc = new HikariConfig(configProps);
        HikariDataSource ds = new HikariDataSource(hc);
        return ds;
    }

    @Bean(name = "TransactionManager")
    @Primary
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(primaryDataSource());
    }

    @Bean(name = "SqlSessionTemplate")
    @Primary
    public SqlSessionFactory devSqlSessionFactory(@Qualifier("DataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        // 设置mapper.xml文件的路径
        sessionFactory.setMapperLocations(resolver.getResources(MAPPER_PATH));
        sessionFactory.setTypeAliasesPackage(ENTITY_PACKAGE);

        //分页插件
        PageInterceptor pageHelper = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);
        Interceptor[] plugins = new Interceptor[]{pageHelper};
        sessionFactory.setPlugins(plugins);

        return sessionFactory.getObject();
    }
}
