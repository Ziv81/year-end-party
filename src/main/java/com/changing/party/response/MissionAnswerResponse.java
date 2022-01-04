package com.changing.party.response;

import com.changing.party.common.enums.AnswerReviewStatus;
import com.changing.party.dto.MissionAnswerDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


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
    private String answer;
    private Integer score;

    /**
     * 圖片題目
     *
     * @param dto
     * @param missionReward
     * @return
     */
    public static MissionAnswerResponse getImageMissionAnswer(MissionAnswerDTO dto, Integer missionReward) {
        return MissionAnswerResponse.builder()
                .missionId(dto.getMissionId())
                .missionStatus(dto.getAnswerReviewStatus().getStatus())
                .missionReward(missionReward)
                .score(dto.getScore())
                .build();
    }

    /**
     * 選擇題與簡答題
     *
     * @param dto
     * @param missionReward
     * @return
     */
    public static MissionAnswerResponse getDefaultMissionAnswer(MissionAnswerDTO dto, Integer missionReward) {
        return MissionAnswerResponse.builder()
                .missionId(dto.getMissionId())
                .missionStatus(dto.getAnswerReviewStatus().getStatus())
                .missionReward(missionReward)
                .answer(dto.getAnswerContent())
                .score(dto.getScore())
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
