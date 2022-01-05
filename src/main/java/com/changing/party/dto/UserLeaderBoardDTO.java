package com.changing.party.dto;

import com.changing.party.model.OnlyNameAndPointModel;
import com.changing.party.response.UserResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Builder
@Log4j2
@Getter
public class UserLeaderBoardDTO {
    List<UserResponse> result;

    public static UserLeaderBoardDTO getUserLeaderBoardModel(List<OnlyNameAndPointModel> userList) {
        List<UserResponse> userModels = new ArrayList<>();
        for (int i = 0; i < userList.size(); i++) {
            int userRank = i + 1;

            //若目前積分與前一位相同，並列排名
            if (!userModels.isEmpty() &&
                    userModels.get(userModels.size() - 1).getUserPoint() == userList.get(i).getUserPoint()) {
                userRank = userModels.get(userModels.size() - 1).getUserRank();
            }
            userModels.add(UserResponse.builder()
                    .userName(userList.get(i).getEnglishName())
                    .userPoint(userList.get(i).getUserPoint())
                    .userRank(userRank)
                    .build());
        }
        return UserLeaderBoardDTO.builder()
                .result(userModels)
                .build();
    }
}
