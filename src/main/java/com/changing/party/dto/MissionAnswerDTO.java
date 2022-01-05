package com.changing.party.dto;

import com.changing.party.common.GlobalVariable;
import com.changing.party.common.enums.AnswerReviewStatus;
import com.changing.party.common.exception.ImageIdNotFoundException;
import com.changing.party.model.MissionAnswerModel;
import com.changing.party.model.MissionImageModel;
import com.changing.party.model.UserModel;
import com.changing.party.repository.MissionImageRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MissionAnswerDTO implements Serializable {

    @Autowired
    MissionImageRepository missionImageRepository;

    private UserModel userModel;
    private Integer missionId;
    private AnswerReviewDTOStatus answerReviewStatus;
    private List<String> answerContent;
    private Integer score;
    private Date answerDate;

    public enum AnswerReviewDTOStatus {
        NOT_ANSWER(0), SUCCESS(1), FAIL(2), REVIEW(3);
        @Getter
        private final int status;

        AnswerReviewDTOStatus(int status) {
            this.status = status;
        }

        public static AnswerReviewDTOStatus convert(AnswerReviewStatus status) {
            switch (status) {
                case SUCCESS:
                    return SUCCESS;
                case FAIL:
                    return FAIL;
                case REVIEW:
                    return REVIEW;
                default:
                    return NOT_ANSWER;
            }
        }
    }

    public MissionAnswerDTO getMissionAnswerModelDTO(MissionAnswerModel missionAnswerModel) throws ImageIdNotFoundException, IOException {
        return MissionAnswerDTO.builder()
                .userModel(missionAnswerModel.getUserModel())
                .missionId(missionAnswerModel.getMissionId())
                .answerReviewStatus(AnswerReviewDTOStatus.convert(missionAnswerModel.getAnswerReviewStatus()))
                .answerContent(getAnswerContent(missionAnswerModel))
                .score(missionAnswerModel.getScore())
                .answerDate(missionAnswerModel.getAnswerDate())
                .build();
    }

    private List<String> getAnswerContent(MissionAnswerModel missionAnswerModel) throws ImageIdNotFoundException, IOException {
        MissionQuestionConfigDTO.MissionType missionType =
                GlobalVariable.getGlobalVariableService().getMISSION_ID_TYPE_MAP().get(missionAnswerModel.getMissionId());
        if (missionType == MissionQuestionConfigDTO.MissionType.TEXT ||
                missionType == MissionQuestionConfigDTO.MissionType.CHOOSE) {
            return Arrays.asList(missionAnswerModel.getAnswerContent());
        } else {
            String[] imageIds =
                    new String(Base64.getDecoder()
                            .decode(missionAnswerModel.getAnswerContent()),
                            StandardCharsets.UTF_8)
                            .split(",");
            List<String> base64ImageContentList = new ArrayList<>();
            for (String imageIdString : imageIds) {
                int imageId = Integer.parseInt(imageIdString);
                MissionImageModel missionImage =
                        missionImageRepository
                                .findById(imageId).orElseThrow(() -> new ImageIdNotFoundException(imageId));
                base64ImageContentList.add(new String(
                        Base64.getEncoder().encode(
                                Files.readAllBytes(Paths.get(
                                        new String(Base64.getDecoder().decode(missionImage.getImagePath()),
                                                StandardCharsets.UTF_8)))),
                        StandardCharsets.UTF_8));
            }
            return base64ImageContentList;
        }
    }
}
