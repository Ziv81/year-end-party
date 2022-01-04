package com.changing.party.controller.management;

import com.changing.party.common.ServerConstant;
import com.changing.party.common.exception.AlreadyOneStackISOpenException;
import com.changing.party.common.exception.StakeIdNotFoundException;
import com.changing.party.common.exception.StakeIsNotOpenException;
import com.changing.party.common.exception.UnknownUpdateStakeStatusOPException;
import com.changing.party.dto.StakeDTO;
import com.changing.party.request.CreateStakeRequest;
import com.changing.party.request.UpdateStakeStatusRequest;
import com.changing.party.response.Response;
import com.changing.party.response.StakeListResponse;
import com.changing.party.service.StakeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequestMapping("/rest/management")
@EnableWebMvc
@Log4j2
public class ManagementStakeController {

    StakeService stakeService;

    public ManagementStakeController(StakeService stakeService) {
        this.stakeService = stakeService;
    }

    /**
     * 回傳賭盤清單
     *
     * @return
     */
    @GetMapping(value = "/stake")
    public Response getStakeList() {
        return Response.builder()
                .errorCode(ServerConstant.SERVER_SUCCESS_CODE)
                .errorMessage(ServerConstant.SERVER_SUCCESS_MESSAGE)
                .data(StakeListResponse.getStakeListResponse(stakeService.getStakeHistoryList()))
                .build();
    }

    @PostMapping(value = "/stake")
    public Response createStake(@RequestBody CreateStakeRequest createStakeRequest) throws AlreadyOneStackISOpenException {
        stakeService.createStake(StakeDTO.getStakeDTO(createStakeRequest));
        return Response.builder()
                .errorCode(ServerConstant.SERVER_SUCCESS_CODE)
                .errorMessage(ServerConstant.SERVER_SUCCESS_MESSAGE)
                .build();
    }

    @PostMapping(value = "/stake/status")
    public Response createStake(@RequestBody UpdateStakeStatusRequest updateStakeStatusRequest)
            throws UnknownUpdateStakeStatusOPException,
            StakeIdNotFoundException,
            StakeIsNotOpenException {
        if (!updateStakeStatusRequest.getOp().equals("stop")) {
            throw new UnknownUpdateStakeStatusOPException(updateStakeStatusRequest.getOp());
        }
        stakeService.stopStake(updateStakeStatusRequest.getData());
        return Response.builder()
                .errorCode(ServerConstant.SERVER_SUCCESS_CODE)
                .errorMessage(ServerConstant.SERVER_SUCCESS_MESSAGE)
                .build();
    }
}
