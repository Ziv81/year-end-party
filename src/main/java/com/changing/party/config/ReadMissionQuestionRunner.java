package com.changing.party.config;

import com.changing.party.common.GlobalVariable;
import com.changing.party.dto.MissionQuestionConfigDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Log4j2
@Component
public class ReadMissionQuestionRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) {
        Resource resource = new ClassPathResource("MissionAnswer.json");
        try (InputStream inputStream = resource.getInputStream()) {
            List<MissionQuestionConfigDTO> missionQuestionConfigDTOS = new ArrayList<>();
            Map<Integer, MissionQuestionConfigDTO.MissionType> missionTypeMap = new HashMap<>();
            Map<Integer, Integer> missionRewardMap = new HashMap<>();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(new String(IOUtils.toByteArray(inputStream), StandardCharsets.UTF_8));
            jsonNode.get("missions").forEach(question -> {
                Integer missionId = question.get("missionId").asInt();
                Integer missionReward = question.get("missionReward").asInt();
                MissionQuestionConfigDTO.MissionType missionType =
                        MissionQuestionConfigDTO.MissionType.getMissionType(question.get("missionType").asInt());
                missionQuestionConfigDTOS.add(MissionQuestionConfigDTO.builder()
                        .missionId(missionId)
                        .missionType(missionType)
                        .missionAnswer(getMissionAnswerList(question.get("missionAnswer")))
                        .missionReward(missionReward)
                        .build());
                missionTypeMap.put(missionId, missionType);
                missionRewardMap.put(missionId, missionReward);
            });
            GlobalVariable.getGlobalVariableService().setMISSION_QUESTION_LIST(missionQuestionConfigDTOS);
            GlobalVariable.getGlobalVariableService().setMISSION_ID_TYPE_MAP(missionTypeMap);
            GlobalVariable.getGlobalVariableService().setMISSION_ID_REWARD_MAP(missionRewardMap);
        } catch (Exception ex) {
            log.error("ReadBinaryQuestionRunner occur ex", ex);
        }
    }

    private List<String> getMissionAnswerList(JsonNode jsonNode) {
        List<String> results = new ArrayList<>();
        Optional.ofNullable(jsonNode).ifPresent(
                notNullJsonNode ->
                        notNullJsonNode.forEach(
                                x -> results.add(x.asText())
                        ));
        return results;
    }
}
