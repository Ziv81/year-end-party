package com.changing.party.binary.model;

import com.changing.party.constant.GlobalVariable;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BinaryAnswerList {
    public static final int STATUS_OPEN = 1;
    public static final int STATUS_CLOSE = 2;
    public static final int STATUS_WAITING = 3;

    int status;
    List<BinaryAnswerDetailDTO> result;

    public static BinaryAnswerList getBinaryAnswerList(BinaryAnswerModel binaryAnswer) {
        List<BinaryAnswerDetailDTO> binaryAnswerDTOs = new ArrayList<>();
        binaryAnswer.getBinaryAnswerDetails().forEach(
                binaryAnswerDetailModel ->
                        binaryAnswerDTOs.add(BinaryAnswerDetailDTO.getBinaryAnswerDetailDTO(binaryAnswerDetailModel))
        );
        return BinaryAnswerList.builder()
                .status(STATUS_CLOSE)
                .result(binaryAnswerDTOs)
                .build();
    }

    /**
     * Binary 允許答題且使用者未回答任何問題，快速回傳答案數量陣列
     */
    public static BinaryAnswerList BINARY_OPEN_AND_USER_NOT_ANSWERED =
            BinaryAnswerList.builder()
                    .status(STATUS_OPEN)
                    .result(GlobalVariable.BINARY_QUESTION_LIST)
                    .build();

    /**
     * Binary 允許答題且使用者已完成回答所有問題，快速回傳待公布物件
     */
    public static BinaryAnswerList BINARY_OPEN_AND_USER_ANSWERED =
            BinaryAnswerList.builder()
                    .status(STATUS_WAITING)
                    .build();

    /**
     * Binary 不允許答題且使用者未回答任何問題
     */
    public static BinaryAnswerList BINARY_CLOSE_AND_USER_NOT_ANSWERED =
            BinaryAnswerList.builder()
                    .status(STATUS_CLOSE)
                    .build();
}
