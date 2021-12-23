package com.changing.party.user;

import com.changing.party.exception.UserIdNotFoundException;
import com.changing.party.model.response.ResponseModel;
import com.changing.party.user.model.User;
import com.changing.party.user.model.UserLeaderBoard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    private static User defaultUser = User.builder()
            .userId(10)
            .userName("Ziv")
            .title("技術處 四組")
            .userPoint(100)
            .userRank(50)
            .build();

    private static List<User> defaultUserList = Arrays.asList(
            User.builder()
                    .userName("Jessica")
                    .userPoint(300)
                    .userRank(10)
                    .build(),
            User.builder()
                    .userName("Doreen")
                    .userPoint(200)
                    .userRank(30)
                    .build(),
            User.builder()
                    .userName("Ziv")
                    .userPoint(100)
                    .userRank(50)
                    .build()
    );

    /**
     * 查詢使用者正向測試案例
     * 驗證 HTTP STATUS 200
     * 驗證回傳結果如預期
     *
     * @throws Exception
     */
    @Test
    void should_return_user_when_get_user_by_id() throws Exception {
        when(userService.getUserById(anyLong())).thenReturn(defaultUser);
        this.mockMvc.perform(get("/api/user/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpectAll(status().isOk(),
                        content().json("{\"errorCode\":0,\"errorMessage\":\"Success\",\"data\":{\"title\":\"技術處 四組\",\"userName\":\"Ziv\",\"userId\":10,\"userPoint\":100,\"userRank\":50}}", true));
    }

    /**
     * 查詢使用者反向，找不到的 UserId
     * 驗證 HTTP STATUS 404
     *
     * @throws Exception
     */
    @Test
    void should_return_http_404_when_user_not_found() throws Exception {
        when(userService.getUserById(anyLong())).thenThrow(UserIdNotFoundException.class);
        this.mockMvc.perform(get("/api/user/999"))
                .andExpect(status().isNotFound());
    }

    /**
     * 查詢使用者積分排行榜
     * 驗證回傳結果格式
     * 驗證 HTTP STATUS 200
     *
     * @throws Exception
     */
    @Test
    void should_return_user_list_when_get_leader_board() throws Exception {
        when(userService.getUserLeaderBoard(Mockito.anyInt()))
                .thenReturn(UserLeaderBoard.builder().result(defaultUserList).build());
        this.mockMvc.perform(get("/api/user/leaderBoard"))
                .andExpectAll(
                        status().isOk(),
                        content().json("{\"errorCode\":0,\"errorMessage\":\"Success\",\"data\":{\"result\":[{\"userRank\":10,\"userName\":\"Jessica\",\"userPoint\":300},{\"userRank\":30,\"userName\":\"Doreen\",\"userPoint\":200},{\"userRank\":50,\"userName\":\"Ziv\",\"userPoint\":100}]}}", true)
                );
    }
}