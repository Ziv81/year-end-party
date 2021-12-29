package com.changing.party.controller.api;

import com.changing.party.response.Response;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequestMapping("/rest/api/mission")
@EnableWebMvc
@Log4j2
public class MissionController {

    /**
     * 回答照片題目
     *
     * @param missionId
     * @return
     */
    @PostMapping(value = "/image/{missionId}")
    Response answerMissionImage(@PathVariable(name = "missionId") int missionId) {
        return null;
    }

    /**
     * 回答簡答題與選擇題
     *
     * @param missionId
     * @return
     */
    @PostMapping(value = "/answer/{missionId}")
    Response answerMission(@PathVariable(name = "missionId") int missionId) {
        return null;
    }
}
