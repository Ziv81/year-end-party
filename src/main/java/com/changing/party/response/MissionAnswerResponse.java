package com.changing.party.response;

import com.changing.party.common.enums.AnswerReviewStatus;
import com.changing.party.dto.MissionAnswerDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Arrays;
import java.util.List;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MissionAnswerResponse {
    private Integer missionId;
    private Integer missionStatus;
    private Integer missionReward;
    private List<String> answer;
    private Integer score;

    /**
     * 圖片題目
     *
     * @param dto
     * @param missionReward
     * @return
     */
    public static MissionAnswerResponse getMissionAnswerResponse(MissionAnswerDTO dto, Integer missionReward) {
        return MissionAnswerResponse.builder()
                .missionId(dto.getMissionId())
                .missionStatus(dto.getAnswerReviewStatus().getStatus())
                .missionReward(missionReward)
                .score(dto.getScore())
                .answer(dto.getAnswerContent())
                .build();
    }

    /**
     * 上傳圖片任務一律直接回傳 Review 審核中
     */
    public static MissionAnswerResponse getMissionAnswerReviewResponse() {
        return MissionAnswerResponse.builder()
                .missionStatus(AnswerReviewStatus.REVIEW.getStatus())
                .score(0)
                .build();
    }

    ;

    /**
     * 上傳答案錯誤
     */
    public static MissionAnswerResponse getMissionWrongAnswerResponse() {
        return MissionAnswerResponse.builder()
                .missionStatus(AnswerReviewStatus.FAIL.getStatus())
                .score(0)
                .build();
    }

    /**
     * 上傳答案正確，需記得更新分數
     */
    public static MissionAnswerResponse getMissionCorrectAnswerResponse() {
        return MissionAnswerResponse.builder()
                .missionStatus(AnswerReviewStatus.SUCCESS.getStatus())
                .score(0)
                .build();
    }
}
