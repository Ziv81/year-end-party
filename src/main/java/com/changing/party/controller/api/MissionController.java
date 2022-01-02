package com.changing.party.controller.api;

import com.changing.party.common.GlobalVariable;
import com.changing.party.common.ServerConstant;
import com.changing.party.common.constraint.annotation.ValidateAnswerMissionIdExist;
import com.changing.party.common.exception.MissionAlreadyAnswerException;
import com.changing.party.common.exception.MissionIDNotFoundException;
import com.changing.party.common.exception.MissionTypeNotMappingException;
import com.changing.party.common.exception.UnknownImageFormatException;
import com.changing.party.dto.MissionAnswerModelDto;
import com.changing.party.request.AnswerMissionImage;
import com.changing.party.response.MissionAnswerResponse;
import com.changing.party.response.Response;
import com.changing.party.service.BinaryService;
import com.changing.party.service.MissionService;
import lombok.AllArgsConstructor;
import lombok.Getter;
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
    public  Response getMissionAnswerHistory() {
        List<MissionAnswerModelDto> responseList = missionService.getMissionAnswerHistory();
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
                .data(MissionAnswerResponse.getMissionAnswerReviewResponse())
                .build();
    }

    /**
     * 回答照片題目
     *
     * @param missionId
     * @return
     */
    @PostMapping(value = "/image/{missionId}")
    public  Response answerMissionImage(@PathVariable(name = "missionId")
                                @ValidateAnswerMissionIdExist
                                        Integer missionId,
                                @RequestBody AnswerMissionImage answerMissionImage)
            throws MissionIDNotFoundException,
            MissionTypeNotMappingException,
            UnknownImageFormatException,
            MissionAlreadyAnswerException {
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
                                   Integer missionId) {
        return null;
    }
}
