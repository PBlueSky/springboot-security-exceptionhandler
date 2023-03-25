package com.example.exceptionhandler.security;

/*
 ** @Author:         blue_sky
 ** @CreateDate:     2022-11-24  20:09
 ** @ProjectName:    springboot-exception-handler
 ** @Package:        com.example.exceptionhandler.config
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Component("ConstumAccessDeniedHandler")
public class ConstumAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, String> responseMap = new HashMap<>();
        responseMap.put("code", HttpStatus.FORBIDDEN.getReasonPhrase());
        responseMap.put("msg","元素未找到");
        response.getWriter().write(objectMapper.writeValueAsString(responseMap));
    }
}
