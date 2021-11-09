package com.les.graphqldemo.controller;

import com.les.graphqldemo.model.User;
import com.les.graphqldemo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.boot.test.GraphQlTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@GraphQlTest(UserController.class)
class UserControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private UserRepository userRepository;

    @Test
    void findById() {
        String id = UUID.randomUUID().toString();
        when(userRepository.findById(eq(id)))
                .thenReturn(Optional.of(new User(id, "a", "b", 1)));

        GraphQlTester.ResponseSpec responseSpec = graphQlTester.query(
                "query{user(id:\"" + id + "\"){id}}"
        ).execute();

        responseSpec.path("data.user.id").entity(String.class).isEqualTo(id);
    }

    @Test
    void findAll() {
        when(userRepository.findAll(eq(PageRequest.of(1, 10))))
                .thenReturn(
                        new PageImpl<>(
                                List.of(
                                        new User("1", "a", "b", 1),
                                        new User("2", "c", "d", 2)
                                )
                        )
                );

        GraphQlTester.ResponseSpec responseSpec = graphQlTester.query(
                "query{users(page:1,size:10){id name email age}}"
        ).execute();

        responseSpec.path("data.users[0]").entity(User.class).isEqualTo(new User("1", "a", "b", 1));
        responseSpec.path("data.users[1].id").entity(String.class).isEqualTo("2");

        String[] ids = responseSpec.path("data.users[*].id").entity(String[].class).get();
        Assertions.assertEquals(2, ids.length);
        Assertions.assertArrayEquals(new String[]{"1", "2"}, ids);
    }

    @Test
    void create() {
        User userToCreate = new User(UUID.randomUUID().toString(), "Les", "les@email.com", 17);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        when(userRepository.save(any(User.class)))
                .thenAnswer(answer ->
                        answer.getArgument(0, User.class)
                );

        GraphQlTester.ResponseSpec responseSpec = graphQlTester.query(
                        "mutation{createUser(name:\"" + userToCreate.getName() + "\", email: \"" + userToCreate.getEmail() + "\", age: " + userToCreate.getAge() + "){id name email age}}"
                )
                .execute();

        verify(userRepository).save(captor.capture());

        String generatedId = captor.getValue().getId();

        responseSpec.path("data.createUser.id").pathExists().entity(String.class).isEqualTo(generatedId);
        responseSpec.path("data.createUser.name").entity(String.class).isEqualTo(userToCreate.getName());
        responseSpec.path("data.createUser.email").entity(String.class).isEqualTo(userToCreate.getEmail());
        responseSpec.path("data.createUser.age").entity(Integer.class).isEqualTo(userToCreate.getAge());

        responseSpec.path("data.createUser").entity(User.class).isEqualTo(new User(generatedId, userToCreate.getName(), userToCreate.getEmail(), userToCreate.getAge()));
    }
}