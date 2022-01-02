package com.changing.party.dto;

import com.changing.party.common.exception.MissionAnswerStatusUnknownException;
import com.changing.party.model.MissionAnswerModel;
import com.changing.party.model.UserModel;
import lombok.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MissionAnswerModelDto implements Serializable {
    private UserModel userModel;
    private Integer missionId;
    private AnswerReviewStatus answerReviewStatus;
    private String answerContent;
    private Integer score;
    private Date answerDate;

    public enum AnswerReviewStatus {
        NOT_ANSWER(0), SUCCESS(1), FAIL(2), REVIEW(3);
        @Getter
        private final int status;

        AnswerReviewStatus(int status) {
            this.status = status;
        }

        public static AnswerReviewStatus convert(MissionAnswerModel.AnswerReviewStatus status) {
            switch (status) {
                case SUCCESS:
                    return SUCCESS;
                case FAIL:
                    return FAIL;
                case REVIEW:
                    return REVIEW;
                default:
                    return NOT_ANSWER;
            }
        }
    }

    public static MissionAnswerModelDto getMissionAnswerModelDTO(MissionAnswerModel missionAnswerModel) {
        return MissionAnswerModelDto.builder()
                .userModel(missionAnswerModel.getUserModel())
                .missionId(missionAnswerModel.getMissionId())
                .answerReviewStatus(AnswerReviewStatus.convert(missionAnswerModel.getAnswerReviewStatus()))
                .answerContent(missionAnswerModel.getAnswerContent())
                .score(missionAnswerModel.getScore())
                .answerDate(missionAnswerModel.getAnswerDate())
                .build();
    }
}
