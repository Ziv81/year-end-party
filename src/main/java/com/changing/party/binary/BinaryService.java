package com.changing.party.binary;

import com.changing.party.binary.model.BinaryQuestionList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
public class BinaryService {

    @Getter
    @AllArgsConstructor
    public enum AnswerStatus {
        OPEN(1),
        CLOSE(2),
        WAITING(3);

        int status;
    }

    public static AnswerStatus binaryStatus;

    public BinaryQuestionList getBinaryQuestionList() {
        return null;
    }
}
