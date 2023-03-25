package com.example.exceptionhandler.config;

/*
 ** @Author:         blue_sky
 ** @CreateDate:     2022-11-23  14:07
 ** @ProjectName:    springboot-exception-handler
 ** @Package:        com.example.exceptionhandler.config
 */

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleItemNotFoundException(
            NoSuchElementException itemNotFoundException,
            WebRequest request
    ){
        HashMap<String, String> responseMap = new HashMap<>();
        responseMap.put("code",HttpStatus.NOT_FOUND.getReasonPhrase());
        responseMap.put("msg","元素未找到");
        return new ResponseEntity<>(responseMap, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ AuthenticationException.class })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Object> handleAuthenticationException(Exception ex) {

        HashMap<String, String> responseMap = new HashMap<>();
        responseMap.put("code",HttpStatus.UNAUTHORIZED.getReasonPhrase());
        responseMap.put("msg","请登录");
        return new ResponseEntity<>(responseMap, HttpStatus.UNAUTHORIZED);
    }

//    @ExceptionHandler({NoSuchElementException.class})
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                             Object body,
                                                             HttpHeaders headers,
                                                             HttpStatus status,
                                                             WebRequest request) {
        return buildErrResponse(ex,headers,status);
    }

    private ResponseEntity<Object>  buildErrResponse(Exception ex,HttpHeaders headers,HttpStatus status){
        HashMap<String, String> responseMap = new HashMap<>();
        responseMap.put("code",status.getReasonPhrase());
        responseMap.put("msg",ex.getMessage());
        return new ResponseEntity<>(responseMap, headers, status);
    }


}
