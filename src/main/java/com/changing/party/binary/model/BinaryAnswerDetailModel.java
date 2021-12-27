package com.changing.party.binary.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "BinaryAnswerDetail", indexes = {
        @Index(name = "idx_binaryanswerdetailmodel", columnList = "questionId, choose")
})
@EqualsAndHashCode(exclude = {"binaryAnswerId"})

public class BinaryAnswerDetailModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer binaryAnswerDetailId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "binaryAnswerId")
    BinaryAnswerModel binaryAnswerId;

    @Column(name = "questionId")
    Integer questionId;

    @Column(name = "choose")
    Integer choose;

    @Column(name = "score")
    Integer score;
}
