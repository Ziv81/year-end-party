package com.changing.party.common;

import com.changing.party.dto.BinaryAnswerDetailDTO;
import com.changing.party.dto.MissionQuestionConfigDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Getter
@Setter
@Component
public class GlobalVariable {

    private static GlobalVariable globalVariable = null;

    public static GlobalVariable getGlobalVariableService() {
        if (globalVariable == null)
            globalVariable = new GlobalVariable();
        return globalVariable;
    }

    private List<String> ADMIN_USER_LIST = new ArrayList<>();
    private List<BinaryAnswerDetailDTO> BINARY_QUESTION_LIST = new ArrayList<>();
    private List<MissionQuestionConfigDTO> MISSION_QUESTION_LIST = new ArrayList<>();
    private Map<Integer, MissionQuestionConfigDTO.MissionType> MISSION_ID_TYPE_MAP = new HashMap<>();
    private Map<Integer, Integer> MISSION_ID_REWARD_MAP = new HashMap<>();
    private List<String> userNameList = new ArrayList<>();
    @Value("${year-end-party.open.time}")
    private String openTimeString;
    private Date openTime;

    public boolean isOpen() {
        return new Date().after(openTime);
    }

    @PostConstruct
    public void postConstruct() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        getGlobalVariableService().openTime=sdf.parse(openTimeString);
    }
}
