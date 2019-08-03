package com.cn.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author chenning
 * @Classname SwaggerConfig
 * @Description swagger配置
 * @Date 2019/7/20 17:06
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //设置包路径
                .apis(RequestHandlerSelectors.basePackage("com.cn.user.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalResponseMessage(RequestMethod.GET,
                        newArrayList(new ResponseMessageBuilder()
                                        .code(0)
                                        .message("请求成功")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(500)
                                        .message("系统异常").build()))
                .globalResponseMessage(RequestMethod.POST,
                        newArrayList(new ResponseMessageBuilder()
                                        .code(0)
                                        .message("请求成功")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(500)
                                        .message("系统异常").build()))
                .globalResponseMessage(RequestMethod.PUT,
                        newArrayList(new ResponseMessageBuilder()
                                        .code(0)
                                        .message("执行成功")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(500)
                                        .message("系统异常").build()))
                .globalResponseMessage(RequestMethod.DELETE,
                        newArrayList(new ResponseMessageBuilder()
                                        .code(0)
                                        .message("请求成功")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(500)
                                        .message("系统异常").build()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("用户服务")
                //描述
                .description("用户API")
                .termsOfServiceUrl("https://github.com/ngcly")
                //创建人
                .contact(new Contact("ngcly", "https://github.com/ngcly", "531237716@qq.com"))
                .version("1.0")
                .build();
    }

}
