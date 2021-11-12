package com.les.common.client;

import com.les.common.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.List;

@Component
public class UserClient {

    private final RestTemplate restTemplate;

    public UserClient(@Value("${user-service.url:http://localhost:9999}") String url) {
        this.restTemplate = new RestTemplate();
        this.restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(url));
    }

    public User findById(String id) {
        return restTemplate.getForObject("/user/{id}", User.class, id);
    }

    public List<User> findAll() {
        return restTemplate.exchange(
                "/user", HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() { }
        ).getBody();
    }
}
