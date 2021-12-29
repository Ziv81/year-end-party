package com.changing.party.service;

import com.changing.party.common.GlobalVariable;
import com.changing.party.common.exception.AnswerBinaryNotOpenException;
import com.changing.party.dto.BinaryAnswerDetailDTO;
import com.changing.party.dto.BinaryAnswerListDTO;
import com.changing.party.model.BinaryAnswerDetailModel;
import com.changing.party.model.BinaryAnswerModel;
import com.changing.party.model.UserModel;
import com.changing.party.repository.BinaryAnswerDetailRepository;
import com.changing.party.repository.BinaryAnswerRepository;
import com.changing.party.repository.BinaryAnswerStatisticsRepository;
import com.changing.party.repository.UserRepository;
import com.changing.party.request.AnswerBinaryRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BinaryServiceTest {
    @Mock
    private BinaryAnswerRepository binaryAnswerRepository;
    @Mock
    private BinaryAnswerDetailRepository binaryAnswerDetailRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private BinaryAnswerStatisticsRepository binaryAnswerStatisticsRepository;
    @Mock
    private UserService userService;

    @InjectMocks
    private BinaryService binaryService;


    @BeforeEach
    void setUp() {
        binaryService = new BinaryService(
                binaryAnswerRepository,
                binaryAnswerDetailRepository,
                binaryAnswerStatisticsRepository,
                userRepository,
                userService);
        List<BinaryAnswerDetailDTO> binaryAnswerDTOList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            binaryAnswerDTOList.add(BinaryAnswerDetailDTO.builder()
                    .questionId(i + 1).build());
        }
        GlobalVariable.getGlobalVariableService().setBINARY_QUESTION_LIST(binaryAnswerDTOList);
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
    void should_return_question_id_list_when_binary_open_and_get_binary_list() {
        BinaryService.binaryAnswerStatus = BinaryService.BinaryAnswerStatus.OPEN;
        when(binaryAnswerRepository.findByAnsweredUser_UserId(Mockito.anyInt())).thenReturn(Optional.empty());
        BinaryAnswerListDTO binaryAnswerList = binaryService.getBinaryAnswerList();
        Assertions.assertNotNull(binaryAnswerList);
        Assertions.assertEquals(BinaryAnswerListDTO.STATUS_OPEN, binaryAnswerList.getStatus());
        Assertions.assertEquals(10, binaryAnswerList.getResult().size());
        Assertions.assertEquals(1, binaryAnswerList.getResult().get(0).getQuestionId());
        Assertions.assertNull(binaryAnswerList.getResult().get(0).getChoose());
        Assertions.assertNull(binaryAnswerList.getResult().get(0).getScore());
    }

    /**
     * 開放作答狀態下，但使用者已經回答完所有題目
     * 僅回傳 Status = 3
     */
    @Test
    void should_return_status_waiting_when_binary_open_and_user_already_answered() {
        BinaryService.binaryAnswerStatus = BinaryService.BinaryAnswerStatus.OPEN;
        when(userService.getUserModelFromSecurityContext()).thenReturn(UserModel.builder().build());
        when(binaryAnswerRepository.findByAnsweredUser_UserId(Mockito.anyInt())).thenReturn(Optional.of(BinaryAnswerModel.builder().build()));
        BinaryAnswerListDTO binaryAnswerList = binaryService.getBinaryAnswerList();
        Assertions.assertNotNull(binaryAnswerList);
        Assertions.assertEquals(BinaryAnswerListDTO.STATUS_WAITING, binaryAnswerList.getStatus());
        Assertions.assertNull(binaryAnswerList.getResult());
    }

    /**
     * 停止作答狀態下，且使用者在作答期間沒有回答任何問題
     * 僅回傳 Status = 2
     */
    @Test
    void should_return_ony_status_close_when_binary_close_and_user_not_answered() {
        BinaryService.binaryAnswerStatus = BinaryService.BinaryAnswerStatus.CLOSE;
        when(binaryAnswerRepository.findByAnsweredUser_UserId(Mockito.anyInt())).thenReturn(Optional.empty());
        BinaryAnswerListDTO binaryAnswerList = binaryService.getBinaryAnswerList();
        Assertions.assertNotNull(binaryAnswerList);
        Assertions.assertEquals(BinaryAnswerListDTO.STATUS_CLOSE, binaryAnswerList.getStatus());
        Assertions.assertNull(binaryAnswerList.getResult());
    }

    /**
     * 停止作答狀態下，且使用者已經回答完所有題目
     * 回傳 Status = 2, 歷程與分數
     */
    @Test
    void should_return_status_close_and_history_and_score_when_binary_close_and_user_already_answered() {
        BinaryService.binaryAnswerStatus = BinaryService.BinaryAnswerStatus.CLOSE;
        when(binaryAnswerRepository.findByAnsweredUser_UserId(Mockito.anyInt())).thenReturn(Optional.of(getDefaultBinaryAnswerModel()));

        BinaryAnswerListDTO binaryAnswerList = binaryService.getBinaryAnswerList();

        Assertions.assertNotNull(binaryAnswerList);
        Assertions.assertEquals(BinaryAnswerListDTO.STATUS_CLOSE, binaryAnswerList.getStatus());
        Assertions.assertNotNull(binaryAnswerList.getResult());

        Assertions.assertEquals(10, binaryAnswerList.getResult().size());

        Assertions.assertEquals(1, binaryAnswerList.getResult().get(0).getQuestionId());
        Assertions.assertEquals(1, binaryAnswerList.getResult().get(0).getChoose());
        Assertions.assertEquals(10, binaryAnswerList.getResult().get(0).getScore());

        Assertions.assertEquals(5, binaryAnswerList.getResult().get(4).getQuestionId());
        Assertions.assertEquals(2, binaryAnswerList.getResult().get(4).getChoose());
        Assertions.assertEquals(20, binaryAnswerList.getResult().get(4).getScore());

        Assertions.assertEquals(8, binaryAnswerList.getResult().get(7).getQuestionId());
        Assertions.assertEquals(3, binaryAnswerList.getResult().get(7).getChoose());
        Assertions.assertEquals(0, binaryAnswerList.getResult().get(7).getScore());
    }

    /**
     * 不允許作答二位元題目時，上傳二位元題目拋出錯誤。
     */
    @Test
    void should_throw_answer_binary_not_open_exception_when_answer_binary_status_not_open() {
        BinaryService.binaryAnswerStatus = BinaryService.BinaryAnswerStatus.CLOSE;
        Assertions.assertThrows(AnswerBinaryNotOpenException.class, () -> binaryService.answerBinaryQuestion(new AnswerBinaryRequest()));
    }

    BinaryAnswerModel getDefaultBinaryAnswerModel() {
        Set<BinaryAnswerDetailModel> binaryAnswerDetailModels = new LinkedHashSet<>();
        for (int i = 0; i < 4; i++) {
            binaryAnswerDetailModels.add(
                    BinaryAnswerDetailModel.builder()
                            .questionId(i + 1)
                            .choose(1)
                            .score(10)
                            .build()
            );
        }

        for (int i = 4; i < 7; i++) {
            binaryAnswerDetailModels.add(
                    BinaryAnswerDetailModel.builder()
                            .questionId(i + 1)
                            .choose(2)
                            .score(20)
                            .build()
            );
        }

        for (int i = 7; i < 10; i++) {
            binaryAnswerDetailModels.add(
                    BinaryAnswerDetailModel.builder()
                            .questionId(i + 1)
                            .choose(3)
                            .score(0)
                            .build()
            );
        }

        return BinaryAnswerModel.builder().binaryAnswerDetails(binaryAnswerDetailModels).build();
    }

}