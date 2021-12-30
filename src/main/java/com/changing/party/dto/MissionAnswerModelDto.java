package com.changing.party.dto;

import com.changing.party.model.MissionAnswerModel;
import com.changing.party.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MissionAnswerModelDto implements Serializable {
    private UserModel userModel;
    private Integer missionId;
    private MissionAnswerModel.AnswerReviewStatus answerReviewStatus;
    private String answerContent;
    private Integer score;
    private Date answerDate;
}
