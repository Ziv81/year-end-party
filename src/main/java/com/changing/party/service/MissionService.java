package com.changing.party.service;

import com.changing.party.common.GlobalVariable;
import com.changing.party.common.exception.MissionAlreadyAnswerException;
import com.changing.party.common.exception.MissionIDNotFoundException;
import com.changing.party.common.exception.MissionTypeNotMappingException;
import com.changing.party.common.exception.UnknownImageFormatException;
import com.changing.party.dto.MissionQuestionConfigDTO;
import com.changing.party.model.MissionAnswerModel;
import com.changing.party.model.UserModel;
import com.changing.party.repository.MissionAnswerRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@Log4j2
@Service
public class MissionService {

    MissionAnswerRepository missionAnswerModelRepository;
    UserService userService;

    public MissionService(MissionAnswerRepository missionAnswerModelRepository, UserService userService) {
        this.missionAnswerModelRepository = missionAnswerModelRepository;
        this.userService = userService;
    }

    @Value("${year-end-party.mission.image.save.path}")
    private String imageSaveDirectory;

    public void answerImageMission(Integer missionId, List<String> answer)
            throws MissionIDNotFoundException,
            MissionTypeNotMappingException,
            UnknownImageFormatException,
            MissionAlreadyAnswerException {

        Optional<MissionQuestionConfigDTO> missionQuestionConfigDTOOptional =
                GlobalVariable.getGlobalVariableService().getMISSION_QUESTION_LIST().stream().filter(x -> x.getMissionId() == missionId).findAny();
        if (!missionQuestionConfigDTOOptional.isPresent()) {
            throw new MissionIDNotFoundException(missionId);
        }

        if (!missionQuestionConfigDTOOptional.get().getMissionType().equals(MissionQuestionConfigDTO.MissionType.IMAGE)) {
            throw new MissionTypeNotMappingException(MissionQuestionConfigDTO.MissionType.IMAGE, missionQuestionConfigDTOOptional.get().getMissionType());
        }

        UserModel user = userService.getUserModelFromSecurityContext();

        if (missionAnswerModelRepository.existsByUserModel_UserIdAndMissionId(user.getUserId(), missionId)) {
            throw new MissionAlreadyAnswerException();
        }
        List<String> savePaths = new ArrayList<>();
        for (int i = 0; i < answer.size(); i++) {
            try {
                savePaths.add(saveImage(answer.get(i), getSaveImageFileName(user, missionId, i)).toString());
            } catch (IOException e) {
                log.error("User name {}. Mission id {}. Image{}", user.getEnglishName(), missionId, answer.get(i));
                log.error("Save image occur exception.", e);
            }
        }
        missionAnswerModelRepository.save(MissionAnswerModel.builder()
                .userModel(user)
                .missionId(missionId)
                .answerReviewStatus(MissionAnswerModel.AnswerReviewStatus.REVIEW)
                .score(0)
                .answerContent(new String(
                        Base64.getEncoder().encode(
                                String.join(",", savePaths)
                                        .getBytes(StandardCharsets.UTF_8)),
                        StandardCharsets.UTF_8))
                .answerDate(new Date())
                .build());
    }

    private String getSaveImageFileName(UserModel user, int missionId, int imageId) {
        return String.format("%s_%s_%s_%s", missionId, user.getEnglishName(), imageId, new SimpleDateFormat("HHmmss").format(new Date()));
    }

    private Path saveImage(String base64File, String fileName) throws IOException, UnknownImageFormatException {
        byte[] imageByteArray = Base64.getDecoder().decode(base64File);
        String imageFormat = "";
        try (ByteArrayInputStream bais = new ByteArrayInputStream(imageByteArray);
             ImageInputStream iis = ImageIO.createImageInputStream(bais)) {
            Iterator<ImageReader> imageReaders = ImageIO.getImageReaders(iis);
            while (imageReaders.hasNext()) {
                imageFormat = imageReaders.next().getFormatName();
            }
        }
        if (!StringUtils.hasLength(imageFormat))
            throw new UnknownImageFormatException(base64File);
        Path path = Paths.get(imageSaveDirectory, String.format("%s.%s", fileName, imageFormat));
        Files.createFile(path);
        return Files.write(path.toAbsolutePath(), imageByteArray);
    }

    public void clearAllMissionAnswerData() {
        missionAnswerModelRepository.deleteAll();
    }
}
