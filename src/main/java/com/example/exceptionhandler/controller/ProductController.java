package com.example.exceptionhandler.controller;

/*
 ** @Author:         blue_sky
 ** @CreateDate:     2022-11-23  13:57
 ** @ProjectName:    springboot-exception-handler
 ** @Package:        com.example.exceptionhandler.controller
 */

import com.example.exceptionhandler.entity.Product;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Slf4j
public class   ProductController {

    @GetMapping("/getProducts")
    public List<Product> getProducts(){
        return Arrays.asList(
          new Product(1,"笔记本电脑"),
          new Product(2,"手机"),
          new Product(3,"显示器"),
          new Product(3,"GitLab CICD 哈哈哈~"),
          new Product(3,"GitLab CICD  自动更新镜像"),
          new Product(3,"自动删除所有的悬空镜像")
        );
    }

    @GetMapping("/getProduct/{id}")
    public Product getProduct(@PathVariable("id") Integer id){
        if(id == 1){
            return new Product(1,"笔记本电脑");
        }else {
            throw new NoSuchElementException("编号为 "+id+" 的商品不存在");
        }
    }

    @GetMapping("/retry")
    @Retryable(value = {NoSuchElementException.class})
    public Product retry(){
        log.error("发生了错误....");
        throw new NoSuchElementException("IllegalArgumentException");
    }

}
