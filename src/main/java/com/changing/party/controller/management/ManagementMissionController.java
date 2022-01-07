package com.changing.party.controller.management;

import com.changing.party.common.ServerConstant;
import com.changing.party.common.exception.ImageIdNotFoundException;
import com.changing.party.common.exception.ImageStatusNotReview;
import com.changing.party.dto.MissionAnswerDTO;
import com.changing.party.model.MissionImageModel;
import com.changing.party.request.MissionImageVerifyListRequest;
import com.changing.party.response.*;
import com.changing.party.service.MissionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/rest/management")
@EnableWebMvc
@Log4j2
public class ManagementMissionController {
    private MissionService missionService;

    public ManagementMissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    @GetMapping(value = "/mission/pending")
    public Response getPendingMissionImages() {
        List<MissionAnswerDTO> missionImageDTOList = missionService.getPendingMissionImage();
        List<PendingMissionImageResponse> pendingMissionImageResponses = new ArrayList<>();
        missionImageDTOList.forEach(x -> pendingMissionImageResponses.add(PendingMissionImageResponse.getPendingMissionImageResponse(x)));
        return Response.builder()
                .errorCode(ServerConstant.SERVER_SUCCESS_CODE)
                .errorMessage(ServerConstant.SERVER_SUCCESS_MESSAGE)
                .data(PendingMissionImageListResponse.builder().result(pendingMissionImageResponses).build())
                .build();
    }

    @GetMapping(value = "/mission/image/{imageId}")
    public ResponseEntity<byte[]> getPendingMissionImageById(@PathVariable(value = "imageId")
                                                             @NotBlank Integer imageId)
            throws ImageIdNotFoundException,
            ImageStatusNotReview,
            IOException {
        MissionImageModel missionImageModel = missionService.getMissionPendingImage(imageId);
        String imageFilePath = new String(Base64.getDecoder().decode(missionImageModel.getImagePath()), StandardCharsets.UTF_8);
        byte[] imageFile = Files.readAllBytes(Paths.get(imageFilePath));
        String mediaType = MediaType.IMAGE_JPEG_VALUE;
        if (imageFilePath.endsWith(".png")) {
            mediaType = MediaType.IMAGE_PNG_VALUE;
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, mediaType)
                .body(imageFile);
    }

    @PostMapping(value = "/mission/verify")
    public ResponseEntity<Response> verifyMissionImage(@Valid @RequestBody MissionImageVerifyListRequest missionImageVerifyListRequest) throws JsonProcessingException {
        List<MissionImageVerifyResponse> missionImageVerifyResponses = missionService.verifyMissionImages(missionImageVerifyListRequest);
        int errorCode =
                missionImageVerifyResponses.stream().anyMatch(x -> !x.getErrorCode().equals(ServerConstant.SERVER_SUCCESS_CODE)) ?
                        ServerConstant.SERVER_FAIL_CODE : ServerConstant.SERVER_SUCCESS_CODE;
        int status = 200;
        if (errorCode != ServerConstant.SERVER_SUCCESS_CODE) {
            status = 400;
        }
        return ResponseEntity.status(status)
                .body(Response.builder()
                        .errorCode(errorCode)
                        .errorMessage(ServerConstant.SERVER_SUCCESS_MESSAGE)
                        .data(MissionImageVerifyListResponse.builder()
                                .result(missionImageVerifyResponses)
                                .amount(missionImageVerifyResponses.size())
                                .build())
                        .build());
    }

    @PostMapping(value = "/mission/clearAll")
    public <T> ResponseEntity<T> clearAllMissionAnswerData() {
        missionService.clearAllMissionAnswerData();
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/missionStop")
    public <T> ResponseEntity<T> missionStop() {
        MissionService.setMissionAnswerStatus(MissionService.MissionAnswerStatus.CLOSE);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/missionStart")
    public <T> ResponseEntity<T> missionStart() {
        MissionService.setMissionAnswerStatus(MissionService.MissionAnswerStatus.OPEN);
        return ResponseEntity.ok().build();
    }
}
