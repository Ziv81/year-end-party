package com.changing.party.binary.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "BinaryAnswerDetail")
public class BinaryAnswerDetailModel {

    public enum ChooseType {
        YES,
        NO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer binaryAnswerDetailId;

    @ManyToOne
    @JoinColumn(name = "binaryAnswerId")
    BinaryAnswerModel binaryAnswerId;

    @Column(name = "questionId")
    Integer questionId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "choose")
    ChooseType choose;
}
