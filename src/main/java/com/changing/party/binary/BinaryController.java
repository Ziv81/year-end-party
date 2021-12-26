package com.changing.party.binary;

import com.changing.party.binary.model.AnswerBinary;
import com.changing.party.constant.ServerConstant;
import com.changing.party.model.response.ResponseModel;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

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
        try {
            binaryService.answerBinaryQuestion(answerBinary);
            return ResponseModel.builder()
                    .errorCode(ServerConstant.SERVER_SUCCESS_CODE)
                    .errorMessage(ServerConstant.SERVER_SUCCESS_MESSAGE)
                    .data("")
                    .build();
        } catch (Exception ex) {
            log.error("answeredBinary occur exception", ex);
            return ResponseModel.builder()
                    .errorCode(ServerConstant.SERVER_FAIL_CODE)
                    .errorMessage(ServerConstant.SERVER_FAIL_MESSAGE)
                    .build();
        }
    }
}
