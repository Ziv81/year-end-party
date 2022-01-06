package com.changing.party.controller.api;

import com.changing.party.common.ServerConstant;
import com.changing.party.request.AnswerBinaryRequest;
import com.changing.party.response.Response;
import com.changing.party.service.BinaryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;

@RestController
@RequestMapping("/rest/api/binary")
@EnableWebMvc
@Log4j2
public class BinaryController {
    private BinaryService binaryService;

    public BinaryController(BinaryService binaryService) {
        this.binaryService = binaryService;
    }

    @GetMapping
    public Response getBinaryList() {
        return Response.builder()
                .errorCode(ServerConstant.SERVER_SUCCESS_CODE)
                .errorMessage(ServerConstant.SERVER_SUCCESS_MESSAGE)
                .data(binaryService.getBinaryAnswerList())
                .build();
    }

    @PostMapping
    public Response answeredBinary(@Valid @RequestBody AnswerBinaryRequest answerBinary) throws JsonProcessingException {
        binaryService.answerBinaryQuestion(answerBinary);
        return Response.builder()
                .errorCode(ServerConstant.SERVER_SUCCESS_CODE)
                .errorMessage(ServerConstant.SERVER_SUCCESS_MESSAGE)
                .build();
    }
}
