package com.changing.party.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "binary_answer_statistics", indexes = {
        @Index(name = "idx_binaryanswerstatistics", columnList = "questionId")
}, uniqueConstraints = {
        @UniqueConstraint(name = "uc_binaryanswerstatistics", columnNames = {"questionId"})
})
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BinaryAnswerStatisticsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "questionId")
    private Integer questionId;

    @Column(name = "chooseYes")
    private Integer chooseYes;

    @Column(name = "chooseNo")
    private Integer chooseNo;

    @Column(name = "chooseSkip")
    private Integer chooseSkip;
}