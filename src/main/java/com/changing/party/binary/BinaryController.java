package com.changing.party.binary;

import com.changing.party.constant.ServerConstant;
import com.changing.party.model.response.ResponseModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequestMapping("/api/binary")
@EnableWebMvc
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
                .data(binaryService.getBinaryQuestionList())
                .build();
    }
}
