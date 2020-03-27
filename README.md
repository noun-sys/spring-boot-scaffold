### 1.基本架构

技术架构:
* SpringBoot + mysql + mybatis + maven
* JDK1.8版本
* Mybatis逆向工程,maven引入逆向工程的插件工具

### 2.DB环境信息
```
DEV  环境：49.235.172.127:3306;      db:noun_account; user&&password: ****
DEMO 环境：47.244.11.216:3306        db:noun_account; user&&password: ****
```
### 6.项目结构
项目结构描述  
&ensp;&ensp;├── Readme.md                       // 帮助文档环境信息  
&ensp;&ensp;├── src.main.java  
&ensp;&ensp;&ensp;&ensp;├── access.controller           // 接口信息根据具体业务分包(如果业务单一暂不分包)  
&ensp;&ensp;&ensp;&ensp;│&ensp;&ensp;├──account                // 账户相关  
&ensp;&ensp;&ensp;&ensp;│&ensp;&ensp;├──good                   // 商品相关  
&ensp;&ensp;&ensp;&ensp;├── base                        // 配置,常量,枚举,拦截器,过滤器等配置信息  
&ensp;&ensp;&ensp;&ensp;│&ensp;&ensp;├── aspect                // 切面相关,实现方式  
&ensp;&ensp;&ensp;&ensp;│&ensp;&ensp;&ensp;&ensp;├── annotation         // 注解  
&ensp;&ensp;&ensp;&ensp;│&ensp;&ensp;├── config                // 配置类相关  
&ensp;&ensp;&ensp;&ensp;│&ensp;&ensp;&ensp;&ensp;├── dataSource         // 数据库  
&ensp;&ensp;&ensp;&ensp;│&ensp;&ensp;&ensp;&ensp;├── swagger            // swagger配置  
&ensp;&ensp;&ensp;&ensp;         ...        
&ensp;&ensp;&ensp;&ensp;│&ensp;&ensp;├── constant              // 常量,枚举,环境等信息  
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;├── exception             // 自定义异常相关  
&ensp;&ensp;&ensp;&ensp;│&ensp;&ensp;├── interceptor           // 拦截器相关  
&ensp;&ensp;&ensp;&ensp;│&ensp;&ensp;├── utils                 // 工具类相关,时间,序列化等  
&ensp;&ensp;&ensp;&ensp;│&ensp;&ensp;└── web                   // 拦截器配置,定制化controller输出,  
&ensp;&ensp;&ensp;&ensp;├── bussiness                   // service层,根据controller可以选择具体分包  
&ensp;&ensp;&ensp;&ensp;│&ensp;&ensp;├── account               // 账户相关  
&ensp;&ensp;&ensp;&ensp;│&ensp;&ensp;└── good                  // 商品相关  
&ensp;&ensp;&ensp;&ensp;├── domin                       // 领域模型对象(除了entity对象)根据需要定制,原则上不允许entity直接透传  
&ensp;&ensp;&ensp;&ensp;│&ensp;&ensp;├── dto                   // 转换对象  
&ensp;&ensp;&ensp;&ensp;│&ensp;&ensp;├── bo
&ensp;&ensp;&ensp;&ensp;│&ensp;&ensp;└── vo  
&ensp;&ensp;&ensp;&ensp;├── job                         // 单独维护job  
&ensp;&ensp;&ensp;&ensp;├── repository                  // 数据库实体类对象(多个数据源,根据业务再次分包)  
&ensp;&ensp;&ensp;&ensp;│&ensp;&ensp;├── entity                // 数据库实体类  
&ensp;&ensp;&ensp;&ensp;│&ensp;&ensp;└── mapper                // mybatis对应的mapper接口       
&ensp;&ensp;&ensp;&ensp;├── thirdparty                  // 依赖三方接口(根据具体业务区分)  
&ensp;&ensp;&ensp;&ensp;│&ensp;&ensp;├── response              // 三方返回参数  
&ensp;&ensp;&ensp;&ensp;│&ensp;&ensp;└── request               // 三方请求参数                
&ensp;&ensp;&ensp;&ensp;└──MpSupplierPaasApplication    // 启动类  
&ensp;&ensp;├── resources  
&ensp;&ensp;&ensp;&ensp;├──generator                    // mybatis逆向工程配置信息  
&ensp;&ensp;&ensp;&ensp;├──mapper                       // mybatis配置的xml文件  
&ensp;&ensp;&ensp;&ensp;application.yml                 // 配置类  
&ensp;&ensp;&ensp;&ensp;logback.xml                     // 日志配置类  
  
