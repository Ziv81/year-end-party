package com.changing.party.user;

import com.changing.party.common.exception.UserIdNotFoundException;
import com.changing.party.repository.UserRepository;
import com.changing.party.model.OnlyNameAndPointModel;
import com.changing.party.response.UserResponse;
import com.changing.party.model.UserModel;
import com.changing.party.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, null);
    }

    @AfterEach
    void tearDown() {
    }

    public static UserModel defaultUserModel = UserModel.builder()
            .userId(10)
            .password("123456789")
            .chineseName("王大明")
            .englishName("Ziv")
            .department("Technical support")
            .jobTitle("Engineer")
            .email("ziv@year.end.party")
            .createDate(new Date())
            .lastLogin(new Date())
            .userPoint(100)
            .build();

    /**
     * 驗證資料庫有回傳使用者時應轉換正確的回前端
     */
    @Test
    void should_return_user_when_get_user_success() {
        when(userRepository.findByUserId(Mockito.anyInt())).thenReturn(defaultUserModel);
        UserResponse user = userService.getUserById(200);
        Assertions.assertEquals(10, user.getUserId());
        Assertions.assertEquals("Ziv", user.getUserName());
        Assertions.assertEquals(100, user.getUserPoint());
        Assertions.assertEquals("Engineer", user.getTitle());
    }

    /**
     * 驗證當資料庫沒有回傳使用者時應拋出 {@link UserIdNotFoundException}
     */
    @Test
    void should_throw_user_id_not_found_exception_when_get_user_fail() {
        when(userRepository.findByUserId(Mockito.anyInt())).thenReturn(null);
        UserIdNotFoundException exception =
                Assertions.assertThrows(UserIdNotFoundException.class, () -> userService.getUserById(-1));
        Assertions.assertEquals("User id -1 not found", exception.getMessage());
    }

    /**
     * 回傳排行榜內容，回傳內容每一位分數都不同，預期就只回傳前三名
     */
    @Test
    void should_return_leader_board_list_when_get_user_list_sort_by_point() {
        OnlyNameAndPointModel firstUser = OnlyNameAndPointStub.builder().englishName("First").userPoint(500).build();
        OnlyNameAndPointModel secondUser = OnlyNameAndPointStub.builder().englishName("Second").userPoint(400).build();
        OnlyNameAndPointModel thirdUser = OnlyNameAndPointStub.builder().englishName("Third").userPoint(300).build();
        OnlyNameAndPointModel forthUser = OnlyNameAndPointStub.builder().englishName("Fourth").userPoint(200).build();
        OnlyNameAndPointModel fifthUser = OnlyNameAndPointStub.builder().englishName("Fifth").userPoint(100).build();

        List<OnlyNameAndPointModel> userModels = Arrays.asList(
                firstUser, secondUser, thirdUser, forthUser, fifthUser
        );

        when(userRepository.findAllByOrderByUserPoint(Mockito.any())).thenReturn(userModels);
        List<UserResponse> userList = userService.getUserLeaderBoard(3).getResult();
        Assertions.assertEquals(3, userList.size());
        Assertions.assertEquals("First", userList.get(0).getUserName());
        Assertions.assertEquals(1, userList.get(0).getUserRank());
        Assertions.assertEquals(500, userList.get(0).getUserPoint());
        Assertions.assertEquals("Second", userList.get(1).getUserName());
        Assertions.assertEquals(2, userList.get(1).getUserRank());
        Assertions.assertEquals(400, userList.get(1).getUserPoint());
        Assertions.assertEquals("Third", userList.get(2).getUserName());
        Assertions.assertEquals(3, userList.get(2).getUserRank());
        Assertions.assertEquals(300, userList.get(2).getUserPoint());
    }

    /**
     * 回傳排行榜內容，回傳內容第二三位分數相同，預期就只回傳前兩名共三人
     */
    @Test
    void should_return_same_point_user_until_total_three_user_when_two_user_same_point() {
        OnlyNameAndPointModel firstUser = OnlyNameAndPointStub.builder().englishName("First").userPoint(500).build();
        OnlyNameAndPointModel secondUser = OnlyNameAndPointStub.builder().englishName("Second").userPoint(400).build();
        OnlyNameAndPointModel thirdUser = OnlyNameAndPointStub.builder().englishName("Third").userPoint(400).build();
        OnlyNameAndPointModel forthUser = OnlyNameAndPointStub.builder().englishName("Fourth").userPoint(200).build();
        OnlyNameAndPointModel fifthUser = OnlyNameAndPointStub.builder().englishName("Fifth").userPoint(100).build();

        List<OnlyNameAndPointModel> userModels = Arrays.asList(
                firstUser, secondUser, thirdUser, forthUser, fifthUser
        );

        when(userRepository.findAllByOrderByUserPoint(Mockito.any())).thenReturn(userModels);
        List<UserResponse> userList = userService.getUserLeaderBoard(3).getResult();
        Assertions.assertEquals(3, userList.size());

        Assertions.assertEquals("First", userList.get(0).getUserName());
        Assertions.assertEquals(1, userList.get(0).getUserRank());
        Assertions.assertEquals(500, userList.get(0).getUserPoint());

        Assertions.assertEquals("Second", userList.get(1).getUserName());
        Assertions.assertEquals(2, userList.get(1).getUserRank());
        Assertions.assertEquals(400, userList.get(1).getUserPoint());

        Assertions.assertEquals("Third", userList.get(2).getUserName());
        Assertions.assertEquals(2, userList.get(2).getUserRank());
        Assertions.assertEquals(400, userList.get(2).getUserPoint());
    }

    /**
     * 回傳排行榜內容，回傳內容第二三四位分數相同，預期需額外加入第四人再回傳
     */
    @Test
    void should_return_same_point_user_even_total_three_user_when_three_user_same_point() {
        OnlyNameAndPointModel firstUser = OnlyNameAndPointStub.builder().englishName("First").userPoint(500).build();
        OnlyNameAndPointModel secondUser = OnlyNameAndPointStub.builder().englishName("Second").userPoint(400).build();
        OnlyNameAndPointModel thirdUser = OnlyNameAndPointStub.builder().englishName("Third").userPoint(300).build();
        OnlyNameAndPointModel forthUser = OnlyNameAndPointStub.builder().englishName("Fourth").userPoint(300).build();
        OnlyNameAndPointModel fifthUser = OnlyNameAndPointStub.builder().englishName("Fifth").userPoint(100).build();

        List<OnlyNameAndPointModel> userModels = Arrays.asList(
                firstUser, secondUser, thirdUser, forthUser, fifthUser
        );

        when(userRepository.findAllByOrderByUserPoint(Mockito.any())).thenReturn(userModels);
        List<UserResponse> userList = userService.getUserLeaderBoard(3).getResult();
        Assertions.assertEquals(4, userList.size());

        Assertions.assertEquals("First", userList.get(0).getUserName());
        Assertions.assertEquals(1, userList.get(0).getUserRank());
        Assertions.assertEquals(500, userList.get(0).getUserPoint());

        Assertions.assertEquals("Second", userList.get(1).getUserName());
        Assertions.assertEquals(2, userList.get(1).getUserRank());
        Assertions.assertEquals(400, userList.get(1).getUserPoint());

        Assertions.assertEquals("Third", userList.get(2).getUserName());
        Assertions.assertEquals(3, userList.get(2).getUserRank());
        Assertions.assertEquals(300, userList.get(2).getUserPoint());

        Assertions.assertEquals("Fourth", userList.get(3).getUserName());
        Assertions.assertEquals(3, userList.get(3).getUserRank());
        Assertions.assertEquals(300, userList.get(3).getUserPoint());
    }
}