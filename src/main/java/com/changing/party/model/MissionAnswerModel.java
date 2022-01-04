package com.changing.party.model;

import com.changing.party.common.enums.AnswerReviewStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mission_answer_model", indexes = {
        @Index(name = "idx_missionanswermodel_userid", columnList = "user_model_user_id"),
        @Index(name = "idx_missionanswermodel_userid_missionid", columnList = "user_model_user_id, missionId"),
        @Index(name = "idx_missionanswermodel", columnList = "answerReviewStatus")
}, uniqueConstraints = {
        @UniqueConstraint(name = "uc_missionanswermodel_missionid_userid", columnNames = {"missionId", "user_model_user_id"})
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MissionAnswerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_model_user_id", nullable = false)
    private UserModel userModel;

    @Column(name = "missionId", nullable = false)
    private Integer missionId;

    @Column(name = "answerReviewStatus", nullable = false)
    private AnswerReviewStatus answerReviewStatus;

    @Column(name = "answer_content", nullable = false)
    private String answerContent;

    @Column(name = "score", nullable = false)
    private Integer score;

    @Temporal(TemporalType.DATE)
    @Column(name = "answerDate", nullable = false)
    private Date answerDate;
}