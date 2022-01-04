package com.changing.party.dto;

import com.changing.party.common.enums.AnswerReviewStatus;
import com.changing.party.model.MissionAnswerModel;
import com.changing.party.model.UserModel;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MissionAnswerDTO implements Serializable {
    private UserModel userModel;
    private Integer missionId;
    private AnswerReviewDTOStatus answerReviewStatus;
    private String answerContent;
    private Integer score;
    private Date answerDate;

    public enum AnswerReviewDTOStatus {
        NOT_ANSWER(0), SUCCESS(1), FAIL(2), REVIEW(3);
        @Getter
        private final int status;

        AnswerReviewDTOStatus(int status) {
            this.status = status;
        }

        public static AnswerReviewDTOStatus convert(AnswerReviewStatus status) {
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

    public static MissionAnswerDTO getMissionAnswerModelDTO(MissionAnswerModel missionAnswerModel) {
        return MissionAnswerDTO.builder()
                .userModel(missionAnswerModel.getUserModel())
                .missionId(missionAnswerModel.getMissionId())
                .answerReviewStatus(AnswerReviewDTOStatus.convert(missionAnswerModel.getAnswerReviewStatus()))
                .answerContent(missionAnswerModel.getAnswerContent())
                .score(missionAnswerModel.getScore())
                .answerDate(missionAnswerModel.getAnswerDate())
                .build();
    }
}
