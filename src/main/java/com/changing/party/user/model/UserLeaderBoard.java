package com.changing.party.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

@Builder
@Log4j2
@Getter
public class UserLeaderBoard {
    List<User> result;

    public static UserLeaderBoard getUserLeaderBoardModel(List<OnlyIdNameAndPointModel> userList, int size) {
        List<User> userModels = new ArrayList<>();
        for (int i = 0; i < userList.size(); i++) {
            int userRank = i + 1;

            //若目前積分與前一位相同，並列排名
            if (!userModels.isEmpty() &&
                    userModels.get(userModels.size() - 1).getUserPoint() == userList.get(i).getUserPoint()) {
                userRank = userModels.get(userModels.size() - 1).getUserRank();
            }
            userModels.add(User.builder()
                    .userName(userList.get(i).getEnglishName())
                    .userPoint(userList.get(i).getUserPoint())
                    .userRank(userRank)
                    .build());

            // 下一個分數不相等且已經達到需要的數量
            if (userList.get(i + 1).getUserPoint() != userList.get(i).getUserPoint()
                    && userModels.size() >= size)
                break;
        }
        return UserLeaderBoard.builder()
                .result(userModels)
                .build();
    }
}
