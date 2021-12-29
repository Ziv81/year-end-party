package com.changing.party.config;

import com.changing.party.common.GlobalVariable;
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
public class ReadAdminUserRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) {
        Resource resource = new ClassPathResource("AdminUserList.json");
        try (InputStream inputStream = resource.getInputStream()) {
            List<String> adminsUserList = new ArrayList<>();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(new String(IOUtils.toByteArray(inputStream), StandardCharsets.UTF_8));
            jsonNode.forEach(node -> adminsUserList.add(node.asText()));
            GlobalVariable.ADMIN_USER_LIST = adminsUserList;
        } catch (Exception ex) {
            log.error("ReadAdminUserRunner occur ex", ex);
        }
    }
}
