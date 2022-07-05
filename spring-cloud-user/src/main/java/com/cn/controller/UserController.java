package com.cn.controller;

import com.cn.pojo.Result;
import com.cn.pojo.UserDO;
import com.cn.pojo.UserDetail;
import com.cn.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.Instant;
import java.util.stream.Collectors;

/**
 * @author chenning
 * @Classname UserController
 * @Description 用户控制层
 * @Date 2019/7/2 20:46
 */
@Tag(name = "UserController", description = "用户相关API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final JwtEncoder encoder;
    private final UserService userService;

    @GetMapping("/token")
    public String token(Authentication authentication) {
        Instant now = Instant.now();
        long expiry = 36000L;
        // @formatter:off
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
        // @formatter:on
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }


    /**
     * 获取用户信息
     */
    @Operation(summary = "获取用户信息", description = "获取当前用户信息")
    @GetMapping("/info")
    public Result<UserDetail> getUserInfo(Principal principal){
        Authentication authentication = (Authentication) principal;
        UserDetail user = (UserDetail) authentication.getPrincipal();
        return Result.success(user);
    }

    /**
     * 获取当前用户凭证信息
     */
    @GetMapping("/principal")
    public Principal principal(Principal principal) {
        return principal;
    }


    @GetMapping("/test")
    public Result test(){
        return Result.success("hello");
    }

    @GetMapping("/info/{username}")
    public Result<UserDO> getUser(@PathVariable("username") String username){
        return userService.getUser(username);
    }
}
