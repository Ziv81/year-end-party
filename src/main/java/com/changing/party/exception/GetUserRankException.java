package com.changing.party.exception;

import com.changing.party.user.model.OnlyPointModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class GetUserRankException extends RuntimeException {
    public GetUserRankException(List<OnlyPointModel> onlyPointModels, int userPoint) {
        super("Can't find equal user point.");
        List<Integer> dataBaseUserPointList = new ArrayList<>();
        onlyPointModels.forEach(x -> dataBaseUserPointList.add(x.getUserPoint()));
        String pointsString;
        try {
            pointsString = new ObjectMapper().writeValueAsString(onlyPointModels);
        } catch (Exception ex) {
            pointsString = "";
            log.error("GetUserRankException writeValueAsString occur exception", ex);
        }
        log.error(String.format("Can't find equal user point. User point list %s, try find user point %s", pointsString, userPoint));
    }
}
