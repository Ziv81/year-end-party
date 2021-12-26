package com.changing.party.binary.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "BinaryAnswerDetail")
public class BinaryAnswerDetailModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer binaryAnswerDetailId;

    @ManyToOne
    @JoinColumn(name = "binaryAnswerId")
    BinaryAnswerModel binaryAnswerId;

    @Column(name = "questionId")
    Integer questionId;

    @Column(name = "choose")
    Integer choose;

    @Column(name = "score")
    Integer score;
}
