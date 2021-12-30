package com.changing.party.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.nio.file.Files;
import java.nio.file.Paths;

@Component
@Log4j2
public class CreateMissionImageDirectoryRunner implements ApplicationRunner {

    @Value("${year-end-party.mission.image.save.path}")
    private String imageSaveDirectory;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (StringUtils.hasText(imageSaveDirectory)) {
            Files.createDirectories(Paths.get(imageSaveDirectory));
            return;
        }
        log.error("Can't find image save directory property.");
    }
}
