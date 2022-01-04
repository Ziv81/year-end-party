package com.changing.party.controller.api;

import com.changing.party.common.GlobalVariable;
import com.changing.party.common.ServerConstant;
import com.changing.party.common.constraint.annotation.ValidateAnswerMissionIdExist;
import com.changing.party.common.exception.*;
import com.changing.party.dto.MissionAnswerDTO;
import com.changing.party.request.AnswerMissionDefault;
import com.changing.party.request.AnswerMissionImageRequest;
import com.changing.party.response.MissionAnswerListResponse;
import com.changing.party.response.MissionAnswerResponse;
import com.changing.party.response.Response;
import com.changing.party.service.MissionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/api/mission")
@EnableWebMvc
@Log4j2
@Validated
public class MissionController {


    private MissionService missionService;

    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    @GetMapping
    public Response getMissionAnswerHistory() {
        List<MissionAnswerDTO> responseList = missionService.getMissionAnswerHistory();
        List<MissionAnswerResponse> missionAnswerResponses = new ArrayList<>();
        responseList.forEach(x -> {
            switch (GlobalVariable.getGlobalVariableService().getMISSION_ID_TYPE_MAP().get(x.getMissionId())) {
                case IMAGE:
                    missionAnswerResponses.add(MissionAnswerResponse.getImageMissionAnswer(
                            x,
                            GlobalVariable
                                    .getGlobalVariableService()
                                    .getMISSION_ID_REWARD_MAP()
                                    .get(x.getMissionId())));
                    break;
                case CHOOSE:
                case TEXT:
                    missionAnswerResponses.add(MissionAnswerResponse.getDefaultMissionAnswer(
                            x,
                            GlobalVariable
                                    .getGlobalVariableService()
                                    .getMISSION_ID_REWARD_MAP()
                                    .get(x.getMissionId())));
            }
        });
        return Response.builder()
                .errorCode(ServerConstant.SERVER_SUCCESS_CODE)
                .errorMessage(ServerConstant.SERVER_SUCCESS_MESSAGE)
                .data(MissionAnswerListResponse.builder()
                        .result(missionAnswerResponses)
                        .status(MissionService.getMissionAnswerStatus().getStatus())
                        .build())
                .build();
    }

    /**
     * 回答照片題目
     *
     * @param missionId
     * @return
     */
    @PostMapping(value = "/image/{missionId}")
    public Response answerMissionImage(@PathVariable(name = "missionId")
                                       @ValidateAnswerMissionIdExist
                                               Integer missionId,
                                       @RequestBody AnswerMissionImageRequest answerMissionImage)
            throws MissionIDNotFoundException,
            MissionTypeNotMappingException,
            UnknownImageFormatException,
            MissionAlreadyAnswerException,
            MissionAnswerImageListSizeNotAcceptException {
        missionService.answerImageMission(missionId, answerMissionImage.getAnswer());
        return Response.builder()
                .errorCode(ServerConstant.SERVER_SUCCESS_CODE)
                .errorMessage(ServerConstant.SERVER_SUCCESS_MESSAGE)
                .data(MissionAnswerResponse.getMissionAnswerReviewResponse())
                .build();
    }

    /**
     * 回答簡答題與選擇題
     *
     * @param missionId
     * @return
     */
    @PostMapping(value = "/answer/{missionId}")
    public Response answerMission(@PathVariable(name = "missionId")
                                  @ValidateAnswerMissionIdExist
                                          Integer missionId,
                                  @RequestBody AnswerMissionDefault answerMissionDefault)
            throws MissionAlreadyAnswerException,
            MissionIDNotFoundException {
        boolean answerCorrect = missionService.answerMission(missionId, answerMissionDefault.getAnswer());
        MissionAnswerResponse missionAnswerResponse = MissionAnswerResponse.getMissionWrongAnswerResponse();
        if (answerCorrect) {
            missionAnswerResponse = MissionAnswerResponse.getMissionCorrectAnswerResponse();
            missionAnswerResponse.setScore(GlobalVariable.getGlobalVariableService().getMISSION_ID_REWARD_MAP().get(missionId));
        }
        return Response.builder()
                .errorCode(ServerConstant.SERVER_SUCCESS_CODE)
                .errorMessage(ServerConstant.SERVER_SUCCESS_MESSAGE)
                .data(missionAnswerResponse)
                .build();
    }
}
