package com.les.spring.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.les")
public class SpringGraphQLMVCApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringGraphQLMVCApplication.class, args);
    }
}
