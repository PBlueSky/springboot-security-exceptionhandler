package com.example.exceptionhandler.security;

/*
 ** @Author:         blue_sky
 ** @CreateDate:     2022-11-24  20:00
 ** @ProjectName:    springboot-exception-handler
 ** @Package:        com.example.exceptionhandler.security
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义Spring Security异常处理，DelegatedAuthenticationEntryPoint将会取代默认的异常处理行为。
 *
 * 默认情况下当发生AuthenticationException的时候，异常处理器 ExceptionTranslationFilter会
 * 执行AuthenticationEntryPoint并启动认证流程，就是向浏览器响应登录页面，在后端服务情况下，是
 * 不需要相应登录页面的，只需要给客户端返回JSON的未授权信息
 *
 * 下面的代码实现会将 AuthenticationException 交给Spring Boot 默认的异常处理解析器处理，这样就可以
 * 使用@ExceptionHandler和@ControllerAdvice进行统一处理返回结果
 */
@Component("delegatedAuthenticationEntryPoint")
public class DelegatedAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException, ServletException {
        resolver.resolveException(request, response, null, authException);
    }
}
