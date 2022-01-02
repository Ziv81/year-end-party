package com.changing.party.common;

import com.changing.party.dto.BinaryAnswerDetailDTO;
import com.changing.party.dto.MissionQuestionConfigDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
