package com.changing.party.config;

import com.changing.party.common.GlobalVariable;
import com.changing.party.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Component
public class ReadUserNameListRunner implements ApplicationRunner {

    UserRepository userRepository;

    public ReadUserNameListRunner(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        List<String> result = new ArrayList<>();
        userRepository.findByOrderByEmailAsc().forEach(x -> result.add(x.getEmail().replace("@changingtec.com", "")));
        GlobalVariable.getGlobalVariableService().setUserNameList(result);
    }
}
