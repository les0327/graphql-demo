package com.les.spring.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.les")
public class SpringGraphQLWebFluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringGraphQLWebFluxApplication.class, args);
    }
}
