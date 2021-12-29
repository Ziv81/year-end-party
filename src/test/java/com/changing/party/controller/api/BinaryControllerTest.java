package com.changing.party.controller.api;

import com.changing.party.common.GlobalVariable;
import com.changing.party.common.JWTUtil;
import com.changing.party.dto.BinaryAnswerDetailDTO;
import com.changing.party.model.LoginUser;
import com.changing.party.service.BinaryService;
import com.changing.party.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BinaryController.class)
class BinaryControllerTest {

    @Autowired
    private MockMvc mockMvc;

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

    /**
     * 使用者回答時檢查長度是否與Server已知的問題數量相同。
     * 若不相同拋出錯誤
     */
    @Test
    void should_throw_arg_not_validate_exception_when_answered_list_size_not_validate() throws Exception {
        this.mockMvc.perform(post("/rest/api/binary")
                        .content("{\"choose\":[1,2,3,2,1,1,3,2,1,1,1]}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .headers(authorizationHeader))
                .andExpectAll(status().isBadRequest());
    }

    /**
     * 使用者回答時檢查內容是否為1-3。
     * 若不相同拋出錯誤
     */
    @Test
    void should_throw_arg_not_validate_exception_when_answered_choose_not_validate() throws Exception {
        List<BinaryAnswerDetailDTO> binaryAnswerDetailDTOList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            binaryAnswerDetailDTOList.add(BinaryAnswerDetailDTO.builder()
                    .questionId(i + 1)
                    .build());
        }
        GlobalVariable.getGlobalVariableService().setBINARY_QUESTION_LIST(binaryAnswerDetailDTOList);
        this.mockMvc.perform(post("/rest/api/binary")
                        .content("{\"choose\":[1,2,3,2,4,1,3,2,1,1]}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .headers(authorizationHeader))
                .andExpectAll(status().isBadRequest());
    }
}