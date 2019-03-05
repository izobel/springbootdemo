package com.zobel.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
/**
 * 一个注解相当于包含下面3个注解
 * @SpringBootConfiguration
 * @EnableAutoConfiguration
 * @ComponentScan
 */

@MapperScan("com.zobel.springbootdemo.mapper")
public class SpringbootdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootdemoApplication.class, args);
    }

}
