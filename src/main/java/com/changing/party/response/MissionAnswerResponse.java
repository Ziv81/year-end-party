package com.changing.party.response;

import com.changing.party.model.MissionAnswerModel;
import lombok.*;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class MissionAnswerResponse {
    private int missionStatus;
    private int score;

    /**
     * 上傳圖片任務一律直接回傳 Review 審核中
     */
    public static MissionAnswerResponse getMissionAnswerReviewResponse() {
        return MissionAnswerResponse.builder()
                .missionStatus(MissionAnswerModel.AnswerReviewStatus.REVIEW.getStatus())
                .score(0)
                .build();
    }

    ;

    /**
     * 上傳答案錯誤
     */
    public static MissionAnswerResponse getMissionWrongAnswerResponse() {
        return MissionAnswerResponse.builder()
                .missionStatus(MissionAnswerModel.AnswerReviewStatus.FAIL.getStatus())
                .score(0)
                .build();
    }

    /**
     * 上傳答案正確，需記得更新分數
     */
    public static MissionAnswerResponse getMissionCorrectAnswerResponse() {
        return MissionAnswerResponse.builder()
                .missionStatus(MissionAnswerModel.AnswerReviewStatus.SUCCESS.getStatus())
                .score(0)
                .build();
    }
}
