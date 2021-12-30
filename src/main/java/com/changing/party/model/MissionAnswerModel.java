package com.changing.party.model;

import com.changing.party.common.converter.MissionAnswerStatusConverter;
import com.changing.party.common.exception.MissionAnswerStatusUnknownException;
import lombok.*;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;

@Entity
@Table(name = "mission_answer_model", indexes = {
        @Index(name = "idx_missionanswermodel_userid", columnList = "user_model_user_id"),
        @Index(name = "idx_missionanswermodel_userid_missionid", columnList = "user_model_user_id, missionId")
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_model_user_id", nullable = false)
    private UserModel userModel;

    @Column(name = "missionId", nullable = false)
    private Integer missionId;

    @Enumerated
    @Column(name = "answerReviewStatus", nullable = false)
    @Convert(converter = MissionAnswerStatusConverter.class)
    private AnswerReviewStatus answerReviewStatus;

    @Column(name = "answer_content", nullable = false)
    private String answerContent;

    @Column(name = "score", nullable = false)
    private Integer score;

    @Temporal(TemporalType.DATE)
    @Column(name = "answerDate", nullable = false)
    private Date answerDate;

    public enum AnswerReviewStatus {
        SUCCESS(1), FAIL(2), REVIEW(3);
        @Getter
        private final int status;

        AnswerReviewStatus(int status) {
            this.status = status;
        }

        public static AnswerReviewStatus convert(Integer status) throws MissionAnswerStatusUnknownException {
            return Arrays.stream(AnswerReviewStatus.values())
                    .filter(x -> x.getStatus() == status)
                    .findAny()
                    .orElseThrow(() -> new MissionAnswerStatusUnknownException(status));
        }
    }
}