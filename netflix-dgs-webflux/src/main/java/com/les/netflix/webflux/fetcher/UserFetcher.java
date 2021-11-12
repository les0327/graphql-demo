package com.les.netflix.webflux.fetcher;

import com.les.common.client.UserReactiveClient;
import com.les.common.model.User;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@DgsComponent
public class UserFetcher {

    private final UserReactiveClient userClient;

    @Autowired
    public UserFetcher(UserReactiveClient userClient) {
        this.userClient = userClient;
    }

    @DgsQuery
    public Mono<User> user(@InputArgument String id) {
        return userClient.findById(id);
    }

    @DgsQuery
    public Flux<User> users() {
        return userClient.findAll();
    }
}
