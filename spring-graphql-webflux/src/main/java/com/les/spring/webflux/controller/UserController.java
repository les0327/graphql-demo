package com.les.spring.webflux.controller;

import com.les.common.client.UserReactiveClient;
import com.les.common.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class UserController {

    private final UserReactiveClient userClient;

    @Autowired
    public UserController(UserReactiveClient userClient) {
        this.userClient = userClient;
    }

    @QueryMapping
    public Flux<User> users() {
        return userClient.findAll();
    }

    @QueryMapping
    public Mono<User> user(@Argument String id) {
        return userClient.findById(id);
    }
}
