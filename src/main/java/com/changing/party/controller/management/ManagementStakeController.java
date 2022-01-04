package com.changing.party.controller.management;

import com.changing.party.common.ServerConstant;
import com.changing.party.common.exception.*;
import com.changing.party.dto.StakeDTO;
import com.changing.party.request.CreateStakeRequest;
import com.changing.party.request.FinishStakeRequest;
import com.changing.party.request.UpdateStakeStatusRequest;
import com.changing.party.response.Response;
import com.changing.party.response.StakeListResponse;
import com.changing.party.service.StakeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;
import javax.validation.constraints.Min;

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
    public Response createStake(@Valid @RequestBody CreateStakeRequest createStakeRequest) throws AlreadyOneStackISOpenException {
        stakeService.createStake(StakeDTO.getStakeDTO(createStakeRequest));
        return Response.builder()
                .errorCode(ServerConstant.SERVER_SUCCESS_CODE)
                .errorMessage(ServerConstant.SERVER_SUCCESS_MESSAGE)
                .build();
    }

    @PostMapping(value = "/stake/status")
    public Response updateStakeStatus(@Valid @RequestBody UpdateStakeStatusRequest updateStakeStatusRequest)
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

    @PostMapping(value = "/stake/{stakeId}/finish")
    public Response finishStake(@PathVariable(value = "stakeId") @Min(1) int stakeId,
                                @Valid @RequestBody FinishStakeRequest finishStakeRequest)
            throws StakeIdNotFoundException,
            StakeIsNotCloseException,
            StakeWinnerPlayIdNotFoundException {
        stakeService.finishStake(stakeId, finishStakeRequest.getWinner());
        return Response.builder()
                .errorCode(ServerConstant.SERVER_SUCCESS_CODE)
                .errorMessage(ServerConstant.SERVER_SUCCESS_MESSAGE)
                .build();
    }

    @PostMapping(value = "/stake/clearAll")
    public Response clearAllStakeData() {
        stakeService.clearAll();
        return Response.builder()
                .errorCode(ServerConstant.SERVER_SUCCESS_CODE)
                .errorMessage(ServerConstant.SERVER_SUCCESS_MESSAGE)
                .build();
    }
}
