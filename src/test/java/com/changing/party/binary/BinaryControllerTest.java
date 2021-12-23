package com.changing.party.binary;

import com.changing.party.binary.model.BinaryQuestionList;
import com.changing.party.binary.model.BinaryQuestionModel;
import com.changing.party.model.response.ResponseModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BinaryController.class)
class BinaryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BinaryService binaryService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * 開放作答狀態下，且使用者也尚未回答任何題目
     * 僅回傳 Status = 1 與 QuestionId list
     * Question list 是為了讓前端可初步確認兩邊題目數量相同
     */
    @Test
    void should_return_question_id_list_when_binary_open_and_get_binary_list() throws Exception {
        List<BinaryQuestionModel> questions = Arrays.asList(
                BinaryQuestionModel.builder()
                        .questionId(1)
                        .build(),
                BinaryQuestionModel.builder()
                        .questionId(2)
                        .build(),
                BinaryQuestionModel.builder()
                        .questionId(3)
                        .build(),
                BinaryQuestionModel.builder()
                        .questionId(4)
                        .build(),
                BinaryQuestionModel.builder()
                        .questionId(5)
                        .build()
        );
        BinaryService.binaryStatus = BinaryService.AnswerStatus.OPEN;
        when(binaryService.getBinaryQuestionList()).thenReturn(BinaryQuestionList.getBinaryQuestionList(questions));
        this.mockMvc.perform(get("/api/binary")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpectAll(status().isOk(),
                        content().json("{\"errorCode\":0,\"errorMessage\":\"Success\",\"data\":{\"status\":1,\"result\":[{\"questionId\":1},{\"questionId\":2},{\"questionId\":3},{\"questionId\":4},{\"questionId\":5}]}}", true));
    }

    /**
     * 開放作答狀態下，但使用者已經回答完所有題目
     * 僅回傳 Status = 3
     */
    @Test
    void should_return_status_waiting_when_binary_open_and_user_already_answered() throws Exception {
        List<BinaryQuestionModel> questions = Arrays.asList(
                BinaryQuestionModel.builder()
                        .questionId(1)
                        .choose(1)
                        .build(),
                BinaryQuestionModel.builder()
                        .questionId(2)
                        .build(),
                BinaryQuestionModel.builder()
                        .questionId(3)
                        .build(),
                BinaryQuestionModel.builder()
                        .questionId(4)
                        .build(),
                BinaryQuestionModel.builder()
                        .questionId(5)
                        .build()
        );
        BinaryService.binaryStatus = BinaryService.AnswerStatus.OPEN;
        when(binaryService.getBinaryQuestionList()).thenReturn(BinaryQuestionList.getBinaryQuestionList(questions));
        this.mockMvc.perform(get("/api/binary")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpectAll(status().isOk(),
                        content().json("{\"errorCode\":0,\"errorMessage\":\"Success\",\"data\":{\"status\":3}}", true));
    }

    /**
     * 停止作答狀態下，且使用者已經回答完所有題目
     * 回傳 Status = 2, 歷程與分數
     */
    @Test
    void should_return_status_close_and_history_and_score_when_binary_close_and_user_already_answered() throws Exception {
        List<BinaryQuestionModel> questions = Arrays.asList(
                BinaryQuestionModel.builder()
                        .questionId(1)
                        .choose(1)
                        .score(20)
                        .build(),
                BinaryQuestionModel.builder()
                        .questionId(2)
                        .choose(2)
                        .score(30)
                        .build(),
                BinaryQuestionModel.builder()
                        .questionId(3)
                        .choose(3)
                        .score(40)
                        .build(),
                BinaryQuestionModel.builder()
                        .questionId(4)
                        .choose(1)
                        .score(50)
                        .build(),
                BinaryQuestionModel.builder()
                        .questionId(5)
                        .choose(2)
                        .score(60)
                        .build()
        );
        BinaryService.binaryStatus = BinaryService.AnswerStatus.CLOSE;
        when(binaryService.getBinaryQuestionList()).thenReturn(BinaryQuestionList.getBinaryQuestionList(questions));
        this.mockMvc.perform(get("/api/binary")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpectAll(status().isOk(),
                        content().json("{\"errorCode\":0,\"errorMessage\":\"Success\",\"data\":{\"status\":2,\"result\":[{\"questionId\":1,\"score\":20,\"choose\":1},{\"questionId\":2,\"score\":30,\"choose\":2},{\"questionId\":3,\"score\":40,\"choose\":3},{\"questionId\":4,\"score\":50,\"choose\":1},{\"questionId\":5,\"score\":60,\"choose\":2}]}}", true));
    }

    /**
     * 停止作答狀態下，且使用者在作答期間沒有回答任何問題
     * 僅回傳 Status = 2
     */
    @Test
    void should_return_ony_status_close_when_binary_close_and_user_not_answered() throws Exception {
        List<BinaryQuestionModel> questions = Arrays.asList(
                BinaryQuestionModel.builder()
                        .questionId(1)
                        .build(),
                BinaryQuestionModel.builder()
                        .questionId(2)
                        .build(),
                BinaryQuestionModel.builder()
                        .questionId(3)
                        .build(),
                BinaryQuestionModel.builder()
                        .questionId(4)
                        .build(),
                BinaryQuestionModel.builder()
                        .questionId(5)
                        .build()
        );
        BinaryService.binaryStatus = BinaryService.AnswerStatus.CLOSE;
        when(binaryService.getBinaryQuestionList()).thenReturn(BinaryQuestionList.getBinaryQuestionList(questions));
        this.mockMvc.perform(get("/api/binary")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpectAll(status().isOk(),
                        content().json("{\"errorCode\":0,\"errorMessage\":\"Success\",\"data\":{\"status\":2}}", true));
    }
}