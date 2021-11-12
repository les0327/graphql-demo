package com.les.spring.mvc.controller;

import com.les.common.client.UserClient;
import com.les.common.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {

    private final UserClient userClient;

    @Autowired
    public UserController(UserClient userClient) {
        this.userClient = userClient;
    }

    @QueryMapping
    public List<User> users() {
        return userClient.findAll();
    }

    @QueryMapping
    public User user(@Argument String id) {
        return userClient.findById(id);
    }
}
