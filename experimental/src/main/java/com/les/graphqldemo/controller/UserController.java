package com.les.graphqldemo.controller;

import com.les.graphqldemo.exception.UserNotFoundException;
import com.les.graphqldemo.model.User;
import com.les.graphqldemo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @QueryMapping(name = "user")
    public User findById(@Argument String id) {
        LOG.info("FindById {}", id);
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " does not exist"));
    }

    @QueryMapping(name = "users")
    public List<User> findAll(@Argument Integer page, @Argument Integer size) {
        LOG.info("FindAll page {}, size {}", page, size);
        return userRepository.findAll(
                        PageRequest.of(
                                page != null ? page : 0,
                                size != null ? size : 10
                        )
                )
                .getContent();
    }

    @MutationMapping(name = "createUser")
    public User create(@Argument String name, @Argument String email, @Argument Integer age) {
        LOG.info("CreateUser {}, {}, {}", name, email, age);
        return userRepository.save(
                new User(UUID.randomUUID().toString(), name, email, age)
        );
    }
}
