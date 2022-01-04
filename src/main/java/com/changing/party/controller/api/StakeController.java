package com.changing.party.controller.api;

import com.changing.party.common.ServerConstant;
import com.changing.party.common.exception.*;
import com.changing.party.dto.UserStakePlayerDTO;
import com.changing.party.dto.UserStakeRoundDTO;
import com.changing.party.request.StakeListRequest;
import com.changing.party.response.Response;
import com.changing.party.response.UserStakeHistoryListResponse;
import com.changing.party.response.UserStakeHistoryResponse;
import com.changing.party.response.UserStakeResponse;
import com.changing.party.service.StakeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/api/stake")
@EnableWebMvc
@Log4j2
public class StakeController {

    private StakeService stakeService;

    public StakeController(StakeService stakeService) {
        this.stakeService = stakeService;
    }


    @GetMapping
    public Response getStake() throws StakeIdNotFoundException {
        return Response.builder()
                .errorCode(ServerConstant.SERVER_SUCCESS_CODE)
                .errorMessage(ServerConstant.SERVER_SUCCESS_MESSAGE)
                .data(UserStakeResponse.getUserStakeResponse(stakeService.getStake()))
                .build();
    }

    @PostMapping("/{stakeId}")
    public Response placeBets(@Valid @PathVariable(value = "stakeId") @Min(1) int stakeId,
                              @Valid @RequestBody StakeListRequest stakeListRequest) throws StakeIdNotFoundException,
            StakePlayerIdNotFoundException,
            StakeIsNotOpenException,
            UserPointNotEnoughException, UserAlreadyPlaceBetsException {
        List<UserStakePlayerDTO> userStakeDTOList = new ArrayList<>();
        stakeListRequest.getStakes().forEach(
                x -> userStakeDTOList.add(
                        UserStakePlayerDTO.builder()
                                .playerId(x.getPlayerId())
                                .point(x.getPoint())
                                .build()));
        stakeService.userPlaceBets(stakeId, userStakeDTOList);
        return Response.builder()
                .errorCode(ServerConstant.SERVER_SUCCESS_CODE)
                .errorMessage(ServerConstant.SERVER_SUCCESS_MESSAGE)
                .build();
    }

    @GetMapping(value = "/history")
    public Response getUserStakeHistory() throws StakeIdNotFoundException {
        List<UserStakeHistoryResponse> userStakeHistoryResponses = new ArrayList<>();
        stakeService
                .getUserStakeHistory()
                .forEach(x -> userStakeHistoryResponses
                        .add(UserStakeHistoryResponse.getUserStakeHistoryResponse(x)));
        return Response.builder()
                .errorCode(ServerConstant.SERVER_SUCCESS_CODE)
                .errorMessage(ServerConstant.SERVER_SUCCESS_MESSAGE)
                .data(UserStakeHistoryListResponse.builder()
                        .result(userStakeHistoryResponses))
                .build();
    }
}
