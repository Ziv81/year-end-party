package com.changing.party.user;

import com.changing.party.exception.UserIdNotFoundException;
import com.changing.party.model.response.ResponseModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.annotation.Validated;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@Validated
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void should_return_user_when_get_user_by_id() throws Exception {
        User user = User.builder()
                .userId(25)
                .userName("Ziv")
                .title("技術處 四組")
                .userPoint(999)
                .userRank(50)
                .build();
        ResponseModel.builder()
                .errorCode(0)
                .errorMessage("Success")
                .data(user);
        when(userService.getUserById(anyLong())).thenReturn(user);
        this.mockMvc.perform(get("/api/user/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpectAll(status().isOk(),
                        content().json("{\"errorCode\":0,\"errorMessage\":\"Success\",\"data\":{\"title\":\"技術處 四組\",\"userName\":\"Ziv\",\"userId\":25,\"userPoint\":999,\"userRank\":50}}"));
    }

    @Test
    void should_return_http_404_when_user_not_found() throws Exception {
        when(userService.getUserById(anyLong())).thenThrow(UserIdNotFoundException.class);
        this.mockMvc.perform(get("/api/user/999")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void should_return_user_list_when_get_user_rank_list() throws Exception {
        List<User> userList = Arrays.asList(
                User.builder()
                        .userName("user1")
                        .userPoint(999)
                        .userRank(1)
                        .build(),
                User.builder()
                        .userName("user2")
                        .userPoint(998)
                        .userRank(2)
                        .build(),
                User.builder()
                        .userName("user3")
                        .userPoint(997)
                        .userRank(3)
                        .build(),
                User.builder()
                        .userName("user4")
                        .userPoint(997)
                        .userRank(3)
                        .build()
        );
        when(userService.getUserByPointSortDesc(anyInt(), anyInt())).thenReturn(userList);
        this.mockMvc.perform(get("/api/user/leaderBoard")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpectAll(status().isOk(),
                        content().json("{\"errorCode\":0,\"errorMessage\":\"Success\",\"data\":{\"result\":[{\"userRank\":1,\"userName\":\"user1\",\"userPoint\":999},{\"userRank\":2,\"userName\":\"user2\",\"userPoint\":998},{\"userRank\":3,\"userName\":\"user3\",\"userPoint\":997},{\"userRank\":3,\"userName\":\"user4\",\"userPoint\":997}]}}"));
    }
}