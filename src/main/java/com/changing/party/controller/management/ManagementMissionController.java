package com.changing.party.controller.management;

import com.changing.party.service.BinaryService;
import com.changing.party.service.MissionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequestMapping("/rest/management")
@EnableWebMvc
@Log4j2
public class ManagementMissionController {
    private MissionService missionService;

    public ManagementMissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    @PostMapping(value = "/mission/clearAll")
    public ResponseEntity clearAllMissionAnswerData() {
        missionService.clearAllMissionAnswerData();
        return ResponseEntity.ok().build();
    }
}
