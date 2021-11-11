package com.les.dgs.fetcher;

import com.les.dgs.model.User;
import com.les.dgs.model.UserInput;
import com.les.dgs.repository.UserRepository;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@DgsComponent
public class UserDatafetcher {

    private final UserRepository userRepository;

    @Autowired
    public UserDatafetcher(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @DgsQuery
    public User user(@InputArgument String id) {
        return userRepository.getById(id);
    }

    @DgsQuery(field = "users")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @DgsMutation(field = "createUser")
    public User create(@InputArgument UserInput userInput) {
        return userRepository.save(
                new User(UUID.randomUUID().toString(), userInput.getName(), userInput.getEmail(), userInput.getAge())
        );
    }

    @DgsMutation
    public boolean deleteUser(@InputArgument("id") String userId) {
        userRepository.deleteById(userId);
        return true;
    }
}
