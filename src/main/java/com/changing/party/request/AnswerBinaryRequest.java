package com.changing.party.request;

import com.changing.party.common.constraint.annotation.ValidateBinaryChoose;
import lombok.Getter;

import java.util.List;

@Getter
public class AnswerBinaryRequest {

    @ValidateBinaryChoose
    List<Integer> choose;
}
