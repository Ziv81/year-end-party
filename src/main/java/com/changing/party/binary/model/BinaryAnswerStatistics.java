package com.changing.party.binary.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "binary_answer_statistics")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BinaryAnswerStatistics {
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