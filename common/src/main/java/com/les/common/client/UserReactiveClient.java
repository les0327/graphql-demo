package com.les.common.client;

import com.les.common.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class UserReactiveClient {

    private final WebClient webClient;

    public UserReactiveClient(@Value("${user-service.url:http://localhost:9999}") String url) {
        this.webClient = WebClient.builder().baseUrl(url).build();
    }

    public Mono<User> findById(String id) {
        return this.webClient.get()
                .uri("/users/{id}", id)
                .retrieve()
                .bodyToMono(User.class);
    }

    public Flux<User> findAll() {
        return this.webClient.get()
                .uri("/users")
                .retrieve()
                .bodyToFlux(User.class);
    }
}
