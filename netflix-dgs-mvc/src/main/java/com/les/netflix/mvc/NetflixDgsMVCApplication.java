package com.les.netflix.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.les")
public class NetflixDgsMVCApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetflixDgsMVCApplication.class, args);
    }
}
