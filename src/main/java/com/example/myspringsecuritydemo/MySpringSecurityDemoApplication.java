package com.example.myspringsecuritydemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.example.myspringsecuritydemo.mapper")
public class MySpringSecurityDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySpringSecurityDemoApplication.class, args);
    }

}
