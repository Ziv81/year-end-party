package com.changing.party.controller.api;

import com.changing.party.common.GlobalVariable;
import com.changing.party.common.JWTUtil;
import com.changing.party.dto.MissionQuestionConfigDTO;
import com.changing.party.model.LoginUser;
import com.changing.party.service.UserService;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MissionController.class)
class MissionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @MockBean
    private UserService userService;

    LoginUser loginUser = Mockito.mock(LoginUser.class);

    HttpHeaders authorizationHeader = new HttpHeaders(
            new LinkedMultiValueMap<String, String>() {
                {
                    put(HttpHeaders.AUTHORIZATION, Arrays.asList(String.format("Bearer %s", JWTUtil.getJWTAccessToken(loginUser))));
                }
            });


    @BeforeEach
    void setUp() {
        GlobalVariable.getGlobalVariableService().setMISSION_QUESTION_LIST(buildDefaultMissionQuestionConfigDTOList());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void should_throw_exception_when_answer_mission_id_not_exist() throws Exception {
        this.mockMvc.perform(post("/rest/api/mission/image/99")
                        .headers(authorizationHeader))
                .andExpectAll(
                        status().isBadRequest(),
                        content().string(containsString("Mission id not mapping")) );
    }


    @Test
    void should_throw_exception_when_answer_mission_type_not_accept() throws Exception {
        this.mockMvc.perform(post("/rest/api/mission/image/1")
                        .headers(authorizationHeader))
                .andExpectAll(
                        status().isBadRequest(),
                        content().string(containsString("Answer content type not accept.")) );
    }

    List<MissionQuestionConfigDTO> buildDefaultMissionQuestionConfigDTOList() {
        return Arrays.asList(
                MissionQuestionConfigDTO.builder().missionId(1).build()
        );
    }
}