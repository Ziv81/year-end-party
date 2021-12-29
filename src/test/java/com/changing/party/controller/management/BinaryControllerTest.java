package com.changing.party.controller.management;

import com.changing.party.service.BinaryService;
import com.changing.party.controller.management.ManagementBinaryController;
import com.changing.party.common.JWTUtil;
import com.changing.party.service.UserService;
import com.changing.party.model.LoginUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ManagementBinaryController.class)
class BinaryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private BinaryService binaryService;

    @MockBean
    BCryptPasswordEncoder bCryptPasswordEncoder;

    LoginUser loginUser = Mockito.mock(LoginUser.class);

    HttpHeaders authorizationHeader = new HttpHeaders(
            new LinkedMultiValueMap<String, String>() {
                {
                    put(HttpHeaders.AUTHORIZATION, Arrays.asList(String.format("Bearer %s", JWTUtil.getJWTAccessToken(loginUser))));
                }
            });

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @WithMockUser(username = "ziv", password = "password")
    void binaryStart() throws Exception {
        mockMvc.perform(post("/rest/management/binaryStart")
                        .headers(authorizationHeader))
                .andExpect(status().isOk());
        Assertions.assertEquals(BinaryService.BinaryAnswerStatus.OPEN, BinaryService.binaryAnswerStatus);
    }

    @Test
    void binaryStop() throws Exception {
        mockMvc.perform(post("/rest/management/binaryStop")
                        .headers(authorizationHeader))
                .andExpect(status().isOk());
        Assertions.assertEquals(BinaryService.BinaryAnswerStatus.CLOSE, BinaryService.binaryAnswerStatus);
    }
}