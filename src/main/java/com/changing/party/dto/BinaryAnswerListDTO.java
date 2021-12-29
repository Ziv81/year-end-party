package com.changing.party.dto;

import com.changing.party.common.GlobalVariable;
import com.changing.party.model.BinaryAnswerModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BinaryAnswerListDTO {
    public static final int STATUS_OPEN = 1;
    public static final int STATUS_CLOSE = 2;
    public static final int STATUS_WAITING = 3;

    int status;
    List<BinaryAnswerDetailDTO> result;

    public static BinaryAnswerListDTO getBinaryAnswerList(BinaryAnswerModel binaryAnswer) {
        List<BinaryAnswerDetailDTO> binaryAnswerDTOs = new ArrayList<>();
        binaryAnswer.getBinaryAnswerDetails().forEach(
                binaryAnswerDetailModel ->
                        binaryAnswerDTOs.add(BinaryAnswerDetailDTO.getBinaryAnswerDetailDTO(binaryAnswerDetailModel))
        );
        return BinaryAnswerListDTO.builder()
                .status(STATUS_CLOSE)
                .result(binaryAnswerDTOs)
                .build();
    }

    /**
     * Binary 允許答題且使用者未回答任何問題，快速回傳答案數量陣列
     */
    public static BinaryAnswerListDTO BINARY_OPEN_AND_USER_NOT_ANSWERED =
            BinaryAnswerListDTO.builder()
                    .status(STATUS_OPEN)
                    .result(GlobalVariable.getGlobalVariableService().getBINARY_QUESTION_LIST())
                    .build();

    /**
     * Binary 允許答題且使用者已完成回答所有問題，快速回傳待公布物件
     */
    public static BinaryAnswerListDTO BINARY_OPEN_AND_USER_ANSWERED =
            BinaryAnswerListDTO.builder()
                    .status(STATUS_WAITING)
                    .build();

    /**
     * Binary 不允許答題且使用者未回答任何問題
     */
    public static BinaryAnswerListDTO BINARY_CLOSE_AND_USER_NOT_ANSWERED =
            BinaryAnswerListDTO.builder()
                    .status(STATUS_CLOSE)
                    .build();
}
