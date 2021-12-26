package com.changing.party.component;

import com.changing.party.binary.model.BinaryAnswerDetailDTO;
import com.changing.party.constant.GlobalVariable;
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

@Log4j2
@Component
public class ReadBinaryQuestionRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) {
        Resource resource = new ClassPathResource("BinaryQuestions.json");
        try (InputStream inputStream = resource.getInputStream()) {
            List<BinaryAnswerDetailDTO> questionList = new ArrayList<>();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(new String(IOUtils.toByteArray(inputStream), StandardCharsets.UTF_8));
            jsonNode.get("questions").forEach(question -> questionList.add(BinaryAnswerDetailDTO.builder().questionId(question.get("questionId").asInt()).build()));
            GlobalVariable.BINARY_QUESTION_LIST = questionList;
        } catch (Exception ex) {
            log.error("ReadBinaryQuestionRunner occur ex", ex);
        }
    }
}
