package com.changing.party.binary.management;

import com.changing.party.binary.BinaryService;
import com.changing.party.binary.model.BinaryAnswerStatisticsResponse;
import com.changing.party.constant.ServerConstant;
import com.changing.party.model.response.ResponseModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequestMapping("/rest/management")
@EnableWebMvc
@Log4j2
public class ManagementBinaryController {

    private BinaryService binaryService;

    public ManagementBinaryController(BinaryService binaryService) {
        this.binaryService = binaryService;
    }

    @PostMapping(value = "/binaryStart")
    public ResponseEntity binaryStart() {
        BinaryService.binaryAnswerStatus = BinaryService.BinaryAnswerStatus.OPEN;
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/binaryStop")
    public ResponseEntity binaryStop() {
        BinaryService.binaryAnswerStatus = BinaryService.BinaryAnswerStatus.CLOSE;
        binaryService.squareUpScore();
        binaryService.updateUserScoreByBinarySquareUp();
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/binaryClearAll")
    public ResponseEntity binaryClearAll() {
        binaryService.clearAllBinaryAnsweredData();
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/binary")
    public ResponseModel getBinaryStatistics() {
        return ResponseModel.builder()
                .errorCode(ServerConstant.SERVER_SUCCESS_CODE)
                .errorMessage(ServerConstant.SERVER_SUCCESS_MESSAGE)
                .data(new BinaryAnswerStatisticsResponse(binaryService.getBinaryAnswerStatisticsData()))
                .build();
    }
}
