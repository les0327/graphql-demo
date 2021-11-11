package com.les.dgs.fetcher;

import com.les.dgs.model.User;
import com.les.dgs.repository.UserRepository;
import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {DgsAutoConfiguration.class, UserDatafetcher.class})
class UserDatafetcherTest {

    @Autowired
    private DgsQueryExecutor dgsQueryExecutor;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void userTest() {

        when(userRepository.getById("1"))
                .thenReturn(new User("1", "name", "email@com", 12));

        String id = dgsQueryExecutor.executeAndExtractJsonPath(
                "query{user(id:\"1\"){id name email age}}",
                "data.user.id"
        );

        assertEquals("1", id);
    }

}