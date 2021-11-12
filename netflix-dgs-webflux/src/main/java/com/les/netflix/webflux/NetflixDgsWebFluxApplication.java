package com.les.netflix.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.les")
public class NetflixDgsWebFluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetflixDgsWebFluxApplication.class, args);
    }
}
