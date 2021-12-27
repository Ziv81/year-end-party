package com.changing.party.binary;

import com.changing.party.binary.model.AnswerBinary;
import com.changing.party.constant.ServerConstant;
import com.changing.party.model.response.ResponseModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/binary")
@EnableWebMvc
@Log4j2
public class BinaryController {
    BinaryService binaryService;

    public BinaryController(BinaryService binaryService) {
        this.binaryService = binaryService;
    }

    @GetMapping
    public ResponseModel getBinaryList() {
        return ResponseModel.builder()
                .errorCode(ServerConstant.SERVER_SUCCESS_CODE)
                .errorMessage(ServerConstant.SERVER_SUCCESS_MESSAGE)
                .data(binaryService.getBinaryAnswerList())
                .build();
    }

    @PostMapping
    public ResponseModel answeredBinary(@Valid @RequestBody AnswerBinary answerBinary) {
        binaryService.answerBinaryQuestion(answerBinary);
        return ResponseModel.builder()
                .errorCode(ServerConstant.SERVER_SUCCESS_CODE)
                .errorMessage(ServerConstant.SERVER_SUCCESS_MESSAGE)
                .data("")
                .build();
    }
}
