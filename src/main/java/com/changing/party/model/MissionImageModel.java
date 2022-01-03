package com.changing.party.model;

import com.changing.party.common.AnswerReviewStatus;
import com.changing.party.common.converter.MissionAnswerStatusConverter;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "mission_image_model", indexes = {
        @Index(name = "idx_missionimagemodel_id", columnList = "id")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MissionImageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "imagePath", length = 2048)
    private String imagePath;

    @Column(name = "reviewStatus", nullable = false)
    private AnswerReviewStatus answerReviewStatus;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_answer_model_id")
    private MissionAnswerModel missionAnswerModel;
}