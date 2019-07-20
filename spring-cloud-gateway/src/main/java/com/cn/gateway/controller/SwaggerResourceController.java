package com.cn.gateway.controller;

import com.cn.gateway.config.SwaggerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger.web.*;

import java.util.List;
import java.util.Optional;

/**
 * @author chenning
 * @Classname SwaggerResourceController
 * @Description swagger聚合接口，三个接口都是swagger-ui.html需要访问的接口
 * @Date 2019/7/20 15:36
 */
@RestController
@RequestMapping("/swagger-resources")
public class SwaggerResourceController {
    private SwaggerProvider swaggerProvider;
    @Autowired(required = false)
    private SecurityConfiguration securityConfiguration;
    @Autowired(required = false)
    private UiConfiguration uiConfiguration;

    @Autowired
    public SwaggerResourceController(SwaggerProvider swaggerProvider) {
        this.swaggerProvider = swaggerProvider;
    }

    @GetMapping("/configuration/security")
    public Mono<ResponseEntity<SecurityConfiguration>> securityConfiguration() {
        return Mono.just(new ResponseEntity<>(Optional.ofNullable(securityConfiguration).orElse(SecurityConfigurationBuilder.builder().build()), HttpStatus.OK));
    }

    @GetMapping("/configuration/ui")
    public Mono<ResponseEntity<UiConfiguration>> uiConfiguration() {
        return Mono.just(new ResponseEntity<>(
                Optional.ofNullable(uiConfiguration).orElse(UiConfigurationBuilder.builder().build()), HttpStatus.OK));
    }

    @GetMapping
    public Mono<ResponseEntity> swaggerResources() {
        return Mono.just((new ResponseEntity<>(swaggerProvider.get(), HttpStatus.OK)));
    }
}
