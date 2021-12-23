package com.changing.party.binary.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BinaryQuestionModel {

    public static int CHOOSE_NOT_CHOOSE = -1;
    public static int CHOOSE_NOT_YES = 1;
    public static int CHOOSE_NOT_NO = 2;
    public static int CHOOSE_NOT_SKIP = 3;

    int questionId;
    int score;

    @Builder.Default
    int choose = CHOOSE_NOT_CHOOSE;
}
