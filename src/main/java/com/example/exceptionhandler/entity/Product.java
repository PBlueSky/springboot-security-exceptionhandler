package com.example.exceptionhandler.entity;

/*
 ** @Author:         blue_sky
 ** @CreateDate:     2022-11-23  13:58
 ** @ProjectName:    springboot-exception-handler
 ** @Package:        com.example.exceptionhandler.entity
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Product {
    int id;
    String name;
}
