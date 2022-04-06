package com.cn.exception;

import com.cn.pojo.RestCode;
import com.cn.pojo.Result;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author chenning
 * @Classname UnauthorizedEntryPoint
 * @Description 无效 token 异常重写类
 * @Date 2019/8/9 16:02
 */
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        response.setContentType("application/json;charset=utf-8");
//        RestCode restCode;
//        if(HttpStatus.NOT_FOUND.value() == response.getStatus()){
//            restCode = RestCode.NOT_FOUND;
//        } else {
//            restCode = RestCode.UNAUTHEN;
//        }
//        PrintWriter out = response.getWriter();
//
//        out.write(JSON.toJSON(Result.failure(restCode)).toString());
//        out.flush();
//        out.close();
    }

}
