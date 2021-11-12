package com.les.netflix.mvc.fetcher;

import com.les.common.client.UserClient;
import com.les.common.model.User;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DgsComponent
public class UserFetcher {

    private final UserClient userClient;

    @Autowired
    public UserFetcher(UserClient userClient) {
        this.userClient = userClient;
    }

    @DgsQuery
    public User user(@InputArgument String id) {
        return userClient.findById(id);
    }

    @DgsQuery
    public List<User> users() {
        return userClient.findAll();
    }
}
