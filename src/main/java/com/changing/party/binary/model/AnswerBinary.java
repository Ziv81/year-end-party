package com.changing.party.binary.model;

import com.changing.party.constraint.annotation.ValidateBinaryChoose;
import lombok.Getter;

import java.util.List;

@Getter
public class AnswerBinary {

    @ValidateBinaryChoose
    List<Integer> choose;
}
