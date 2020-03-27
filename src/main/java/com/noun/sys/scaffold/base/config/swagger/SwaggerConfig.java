package com.noun.sys.scaffold.base.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author:gaoxu
 * @create:2020-03-16 15:31
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.noun.sys.scaffold.access.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("spring-boot-scaffold API Title")
                .description("spring-boot-scaffold 接口文档")
                .termsOfServiceUrl(" API terms of service")
                .version("1.0.0")
                .license("com.spring.boot.scaffold")
                .contact(new Contact("spring-boot-scaffold", "www.scaffold.com", "scaffold@spring.com"))
                .build();
    }
}
