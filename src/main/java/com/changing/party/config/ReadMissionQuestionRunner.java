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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Component
public class ReadMissionQuestionRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) {
        Resource resource = new ClassPathResource("MissionAnswer.json");
        try (InputStream inputStream = resource.getInputStream()) {
            List<MissionQuestionConfigDTO> missionQuestionConfigDTOS = new ArrayList<>();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(new String(IOUtils.toByteArray(inputStream), StandardCharsets.UTF_8));
            jsonNode.get("missions").forEach(question -> missionQuestionConfigDTOS.add(MissionQuestionConfigDTO.builder()
                    .missionId(question.get("missionId").asInt())
                    .missionType(MissionQuestionConfigDTO.MissionType.getMissionType(question.get("missionType").asInt()))
                    .missionAnswer(getMissionAnswerList(question.get("missionAnswer")))
                    .missionReward(question.get("missionReward").asInt())
                    .build()));
            GlobalVariable.getGlobalVariableService().setMISSION_QUESTION_LIST(missionQuestionConfigDTOS);
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
