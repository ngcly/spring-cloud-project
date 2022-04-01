//package com.cn.config;
//
//import com.cn.exception.CustomAccessDeniedHandler;
//import com.cn.exception.UnauthorizedEntryPoint;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
//
///**
// * @author chenning
// * @Classname ResourceServerConfig
// * @Description 资源配置
// * @Date 2019/6/25 16:42
// */
//@Configuration
//@EnableResourceServer
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//    @Autowired
//    private RedisConnectionFactory connectionFactory;
//
//    @Bean
//    public TokenStore tokenStore() {
//        RedisTokenStore redis = new RedisTokenStore(connectionFactory);
//        return redis;
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//
//        http
//                .csrf().disable()
//                .exceptionHandling()
//                .accessDeniedHandler(new CustomAccessDeniedHandler())
//                .authenticationEntryPoint(new UnauthorizedEntryPoint())
//                .and()
//                .authorizeRequests()
//                //以下地址不进行认证 直接通过，也可以将以下地址配置到配置文件中
//                .antMatchers("/actuator/**","/druid/**","/v2/api-docs/**","/test/**").permitAll()
//                .anyRequest().authenticated();
//
//    }
//
////    @Override
////    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
////        // token失效处理器
////        resources.tokenStore(tokenStore()).authenticationEntryPoint(new UnauthorizedEntryPoint());
////    }
//}
