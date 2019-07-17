package com.cn.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author chenning
 * @Classname SwaggerConfig
 * @Description swagger 配置
 * @Date 2019/7/2 20:37
 */
@Configuration
@EnableSwagger2
@PropertySource("classpath:swagger.properties")
public class SwaggerConfig {
    @Value("${spring.application.name}")
    public String application;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //设置包路径
                .apis(RequestHandlerSelectors.basePackage("com.cn.controller"))
                .paths(PathSelectors.any())
                .build();
//                .securityContexts(Collections.singletonList(securityContext()))
//                .securitySchemes(Collections.singletonList(securityScheme()));
    }

    private ApiInfo apiInfo() {
        switch (application) {
            case "spring-cloud-user":
                application = "用户服务API管理";
                break;
            case "spring-cloud-auth":
                application = "授权服务API管理";
                break;
            default:application="网关服务API管理";
                break;
        }
        return new ApiInfoBuilder()
                //页面标题
                .title(application)
                //描述
                .description(application)
                .termsOfServiceUrl("https://github.com/ngcly")
                //创建人
                .contact(new Contact("ngcly", "https://github.com/ngcly", "531237716@qq.com"))
                .version("1.0")
                .build();
    }

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder().docExpansion(DocExpansion.LIST).operationsSorter(OperationsSorter.ALPHA)
                .defaultModelRendering(ModelRendering.MODEL).build();
    }

//    /**
//     * 这个类决定了使用哪种认证方式，这里使用密码模式
//     */
//    private SecurityScheme securityScheme() {
//        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant(AUTH_SERVER);
//
//        return new OAuthBuilder()
//                .name("Oauth2")
//                .grantTypes(Collections.singletonList(grantType))
//                .scopes(Arrays.asList(scopes()))
//                .build();
//    }
//
//    /**
//     * 这里设置 swagger2 认证的安全上下文
//     */
//    private SecurityContext securityContext() {
//        return SecurityContext.builder()
//                .securityReferences(Collections.singletonList(new SecurityReference("Oauth2", scopes())))
//                .forPaths(PathSelectors.any())
//                .build();
//    }
//
//    /**
//     * 这里是写允许认证的scope
//     */
//    private AuthorizationScope[] scopes() {
//        return new AuthorizationScope[]{
//                new AuthorizationScope("all", "All scope is trusted!")
//        };
//    }

}
