package com.changing.party.service;

import com.changing.party.common.GlobalVariable;
import com.changing.party.common.ServerConstant;
import com.changing.party.common.enums.AnswerReviewStatus;
import com.changing.party.common.enums.LogType;
import com.changing.party.common.exception.*;
import com.changing.party.dto.MissionAnswerDTO;
import com.changing.party.dto.MissionQuestionConfigDTO;
import com.changing.party.model.MissionAnswerModel;
import com.changing.party.model.MissionImageModel;
import com.changing.party.model.ServiceLog;
import com.changing.party.model.UserModel;
import com.changing.party.repository.MissionAnswerRepository;
import com.changing.party.repository.MissionImageRepository;
import com.changing.party.request.MissionImageVerifyListRequest;
import com.changing.party.response.MissionImageVerifyResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
@Transactional
public class MissionService {

    private static final String MISSION_IMAGE_VERIFY_OP_APPROVE = "approve";
    private static final Object KEY = "";

    @Getter
    @AllArgsConstructor
    public enum MissionAnswerStatus {
        OPEN(1),
        CLOSE(2);
        int status;
    }

    private static MissionAnswerStatus missionAnswerStatus = MissionAnswerStatus.OPEN;

    MissionAnswerRepository missionAnswerRepository;
    MissionImageRepository missionImageRepository;
    UserService userService;
    ServiceLogService serviceLogService;

    public MissionService(MissionAnswerRepository missionAnswerRepository,
                          MissionImageRepository missionImageRepository,
                          UserService userService,
                          ServiceLogService serviceLogService) {
        this.missionAnswerRepository = missionAnswerRepository;
        this.missionImageRepository = missionImageRepository;
        this.userService = userService;
        this.serviceLogService = serviceLogService;
    }

    @Value("${year-end-party.mission.image.save.path}")
    private String imageSaveDirectory;

    /**
     * ??????????????????
     *
     * @param missionImageVerifyListRequest
     */
    public List<MissionImageVerifyResponse> verifyMissionImages(MissionImageVerifyListRequest missionImageVerifyListRequest) throws JsonProcessingException {
        synchronized (KEY) {
            List<MissionImageVerifyResponse> missionImageVerifyResponses = new ArrayList<>();
            missionImageVerifyListRequest.getData().forEach(
                    x -> {
                        Integer errorCode;
                        String errorMessage;
                        try {
                            MissionAnswerModel missionAnswerModel =
                                    missionAnswerRepository.findByUserModel_UserIdAndMissionId(x.getUserId(), x.getMissionId())
                                            .orElseThrow(() -> new UserIdWithMissionidNotFoundException(x.getUserId(), x.getMissionId()));
                            if (missionAnswerModel.getAnswerReviewStatus() != AnswerReviewStatus.REVIEW) {
                                throw new MissionAlreadyReviewException();
                            }
                            AnswerReviewStatus reviewStatus =
                                    missionImageVerifyListRequest.getOp().equals(MISSION_IMAGE_VERIFY_OP_APPROVE) ?
                                            AnswerReviewStatus.SUCCESS : AnswerReviewStatus.FAIL;
                            int score = missionImageVerifyListRequest.getOp().equals(MISSION_IMAGE_VERIFY_OP_APPROVE) ?
                                    GlobalVariable.getGlobalVariableService().getMISSION_ID_REWARD_MAP().get(missionAnswerModel.getMissionId()) :
                                    0;
                            List<Integer> imageIds = new ArrayList<>();
                            Arrays.asList(new String(Base64.getDecoder().decode(missionAnswerModel.getAnswerContent()), StandardCharsets.UTF_8).split(","))
                                    .forEach(y -> imageIds.add(Integer.parseInt(y)));
                            int updateRows = missionImageRepository.updateAnswerReviewStatusByQuestionIdList(reviewStatus, imageIds);
                            log.debug("Image list size {}, update rows {}", imageIds.size(), updateRows);

                            missionAnswerModel.setAnswerReviewStatus(reviewStatus);
                            missionAnswerModel.setScore(score);
                            missionAnswerRepository.save(missionAnswerModel);
                            userService.updateUserPoint(missionAnswerModel.getUserModel(), score);
                            serviceLogService.logService(LogType.MISSION,
                                    userService.getUserModelFromSecurityContext(),
                                    ServiceLog.MISSION_VERIFY_IMAGE,
                                    String.format("Image verify %s ,user update point %s", reviewStatus, score));

                            errorCode = ServerConstant.SERVER_SUCCESS_CODE;
                            errorMessage = ServerConstant.SERVER_SUCCESS_MESSAGE;
                        } catch (UserIdWithMissionidNotFoundException e) {
                            log.error("User id with mission id data not found {}", e.getMessage());
                            errorCode = ServerConstant.SERVER_FAIL_CODE;
                            errorMessage = "User id with mission id not found";
                        } catch (MissionAlreadyReviewException e) {
                            log.error("Image already review");
                            errorCode = ServerConstant.SERVER_FAIL_CODE;
                            errorMessage = "Already review by other people.";
                        }
                        missionImageVerifyResponses.add(
                                MissionImageVerifyResponse.builder()
                                        .targetId(x.getMissionId())
                                        .targetName(x.getUserId())
                                        .errorCode(errorCode)
                                        .errorMessage(errorMessage)
                                        .build());
                    }
            );
            serviceLogService.logService(LogType.MISSION,
                    userService.getUserModelFromSecurityContext(),
                    ServiceLog.MISSION_VERIFY_IMAGE,
                    new ObjectMapper().writeValueAsString(missionImageVerifyListRequest));
            return missionImageVerifyResponses;
        }
    }

    /**
     * ?????? image id ???????????????????????????
     *
     * @param imageId
     * @return
     */
    public MissionImageModel getMissionPendingImage(Integer imageId)
            throws ImageIdNotFoundException,
            ImageStatusNotReview {
        MissionImageModel missionImageModel = missionImageRepository.findById(imageId)
                .orElseThrow(() -> new ImageIdNotFoundException(imageId));

        if (!missionImageModel.getAnswerReviewStatus().equals(AnswerReviewStatus.REVIEW)) {
            throw new ImageStatusNotReview(imageId);
        }
        return missionImageModel;
    }

    /**
     * ???????????????????????????
     *
     * @return
     */
    public List<MissionAnswerDTO> getPendingMissionImage() {
        List<MissionAnswerDTO> missionImageModelDTOs = new ArrayList<>();
        missionAnswerRepository.findByAnswerReviewStatus(AnswerReviewStatus.REVIEW)
                .forEach(x -> {
                    try {
                        missionImageModelDTOs.add(new MissionAnswerDTO().getMissionAnswerModelDTO(x, false));
                    } catch (ImageIdNotFoundException e) {
                        log.error("getPendingMissionImage image id not found.", e);
                    } catch (IOException e) {
                        log.error("getPendingMissionImage occur IOException.", e);
                    }
                });
        return missionImageModelDTOs;
    }

    /**
     * ????????????????????????
     *
     * @return
     */
    public List<MissionAnswerDTO> getMissionAnswerHistory() throws ImageIdNotFoundException, IOException {
        Set<MissionAnswerModel> missionAnswerModelStream = missionAnswerRepository
                .findByUserModel_UserIdOrderByMissionIdAsc(userService.getUserModelFromSecurityContext().getUserId());
        List<MissionAnswerDTO> missionAnswerModelDTOs = new ArrayList<>();
        for (int i = 0; i < GlobalVariable.getGlobalVariableService().getMISSION_QUESTION_LIST().size(); i++) {
            int missionId = i + 1;
            Optional<MissionAnswerModel> missionAnswerModel = missionAnswerModelStream
                    .stream()
                    .filter(x -> x.getMissionId().equals(missionId))
                    .findFirst();
            if (missionAnswerModel.isPresent()) {
                //???????????????????????????
                missionAnswerModelDTOs.add(
                        new MissionAnswerDTO(missionImageRepository).getMissionAnswerModelDTO(missionAnswerModel.get(), true));
            } else {
                //????????????????????????
                missionAnswerModelDTOs.add(
                        MissionAnswerDTO.builder()
                                .missionId(missionId)
                                .answerReviewStatus(MissionAnswerDTO.AnswerReviewDTOStatus.NOT_ANSWER)
                                .build()
                );
            }
        }
        return missionAnswerModelDTOs;
    }

    /**
     * ???????????????????????????
     *
     * @param missionId ????????????
     * @param answer    ????????????
     * @throws MissionIDNotFoundException     ?????????????????????
     * @throws MissionTypeNotMappingException ???????????????????????????
     * @throws UnknownImageFormatException    ???????????????????????????
     * @throws MissionAlreadyAnswerException  ?????????????????????
     */
    public void answerImageMission(Integer missionId, List<String> answer)
            throws MissionIDNotFoundException,
            MissionTypeNotMappingException,
            UnknownImageFormatException,
            MissionAlreadyAnswerException,
            MissionAnswerImageListSizeNotAcceptException {

        checkMissionIdAndTypeAndSize(missionId, MissionQuestionConfigDTO.MissionType.IMAGE, answer.size());
        UserModel user = userService.getUserModelFromSecurityContext();
        checkUserNotAnswered(user.getUserId(), missionId);

        MissionAnswerModel missionAnswer = missionAnswerRepository.save(MissionAnswerModel.builder()
                .userModel(user)
                .missionId(missionId)
                .answerReviewStatus(AnswerReviewStatus.REVIEW)
                .score(0)
                .answerContent("")
                .answerDate(new Date())
                .build());

        List<String> missionImageList = new ArrayList<>();
        for (int i = 0; i < answer.size(); i++) {
            try {
                String savePath = saveImage(answer.get(i), getSaveImageFileName(user, missionId, i)).toString();
                missionImageList.add(String.valueOf(
                        missionImageRepository.save(
                                MissionImageModel.builder()
                                        .answerReviewStatus(AnswerReviewStatus.REVIEW)
                                        .imagePath(new String(Base64.getEncoder().encode(savePath.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8))
                                        .missionAnswerModel(missionAnswer)
                                        .build()).getId()));
            } catch (IOException e) {
                log.error("User name {}. Mission id {}. Image{}", user.getEnglishName(), missionId, answer.get(i));
                log.error("Save image occur exception.", e);
            }
        }
        String imageIdList = new String(
                Base64.getEncoder().encode(
                        String.join(",", missionImageList)
                                .getBytes(StandardCharsets.UTF_8)),
                StandardCharsets.UTF_8);
        missionAnswer.setAnswerContent(imageIdList);
        missionAnswerRepository.save(missionAnswer);
        serviceLogService.logService(LogType.MISSION,
                userService.getUserModelFromSecurityContext(),
                ServiceLog.MISSION_ANSWER_IMAGE,
                imageIdList);
    }

    /**
     * ???????????????????????????
     *
     * @param missionId ????????????
     * @param answer    ????????????
     */
    public boolean answerMission(Integer missionId, String answer)
            throws MissionIDNotFoundException,
            MissionAlreadyAnswerException {
        try {
            MissionQuestionConfigDTO missionQuestionConfigDTO = checkMissionIdExist(missionId);
            UserModel user = userService.getUserModelFromSecurityContext();
            checkUserNotAnswered(user.getUserId(), missionId);
            return answerMission(user, missionQuestionConfigDTO, answer);
        } finally {
            serviceLogService.logService(LogType.MISSION,
                    userService.getUserModelFromSecurityContext(),
                    ServiceLog.MISSION_ANSWER,
                    String.format("Mission id : %s,Answer : %s", missionId, answer));
        }
    }

    /**
     * ???????????????????????????
     *
     * @param user                     ???????????????
     * @param missionQuestionConfigDTO ?????????????????????????????????
     * @param answer                   ????????????
     */
    private boolean answerMission(UserModel user, MissionQuestionConfigDTO missionQuestionConfigDTO, String answer) {
        boolean answerCorrect = missionQuestionConfigDTO.getMissionAnswer().contains(answer);
        int score = answerCorrect ? missionQuestionConfigDTO.getMissionReward() : 0;
        missionAnswerRepository.save(MissionAnswerModel.builder()
                .userModel(user)
                .missionId(missionQuestionConfigDTO.getMissionId())
                .answerReviewStatus(answerCorrect ? AnswerReviewStatus.SUCCESS : AnswerReviewStatus.FAIL)
                .score(score)
                .answerContent(answer)
                .answerDate(new Date())
                .build());
        userService.updateUserPoint(user, score);
        String logContent = String.format("User answer %s, point update %s.", answerCorrect ? "correct" : "incorrect", score);
        serviceLogService.logService(LogType.MISSION,
                userService.getUserModelFromSecurityContext(),
                ServiceLog.MISSION_ANSWER,
                String.format("Mission id : %s,Answer : %s", missionQuestionConfigDTO.getMissionId(), answer));
        return answerCorrect;
    }


    /**
     * ???????????????????????????????????????
     *
     * @param userId          ???????????????
     * @param answerMissionId ????????????????????? mission id
     * @throws MissionAlreadyAnswerException
     */
    private void checkUserNotAnswered(Integer userId, Integer answerMissionId) throws MissionAlreadyAnswerException {
        if (missionAnswerRepository.existsByUserModel_UserIdAndMissionId(userId, answerMissionId)) {
            throw new MissionAlreadyAnswerException();
        }
    }

    /**
     * ??????????????????????????????????????????
     *
     * @param missionId         mission id
     * @param actualMissionType ????????????????????????
     * @throws MissionIDNotFoundException     mission id ?????????
     * @throws MissionTypeNotMappingException mission id ????????????????????????????????????
     */
    private void checkMissionIdAndTypeAndSize(Integer missionId,
                                              MissionQuestionConfigDTO.MissionType actualMissionType,
                                              Integer answerListSize)
            throws MissionIDNotFoundException,
            MissionTypeNotMappingException,
            MissionAnswerImageListSizeNotAcceptException {
        MissionQuestionConfigDTO missionQuestionConfigDTO = checkMissionIdExist(missionId);
        checkMissionTypeAccept(missionQuestionConfigDTO.getMissionType(), actualMissionType);
        checkAnswerSize(missionQuestionConfigDTO, answerListSize);
    }

    /**
     * ????????????????????????????????????????????????
     *
     * @param missionQuestionConfigDTO
     * @param answerListSize
     */
    private void checkAnswerSize(MissionQuestionConfigDTO missionQuestionConfigDTO, Integer answerListSize) throws MissionAnswerImageListSizeNotAcceptException {
        if (!missionQuestionConfigDTO.getMissionAnswer().contains(String.valueOf(answerListSize))) {
            throw new MissionAnswerImageListSizeNotAcceptException();
        }
    }


    /**
     * ?????? mission id ??????
     *
     * @param missionId ???????????? mission id
     * @throws MissionIDNotFoundException mission id ?????????
     */
    private MissionQuestionConfigDTO checkMissionIdExist(Integer missionId) throws MissionIDNotFoundException {
        Optional<MissionQuestionConfigDTO> missionQuestionConfigDTOOptional =
                GlobalVariable.getGlobalVariableService().getMISSION_QUESTION_LIST().stream().filter(x -> x.getMissionId() == missionId).findAny();
        if (!missionQuestionConfigDTOOptional.isPresent()) {
            throw new MissionIDNotFoundException(missionId);
        }
        return missionQuestionConfigDTOOptional.get();
    }

    /**
     * ?????? mission type ?????????
     *
     * @param exceptMissionType ????????????????????????
     * @param actualMissionType ?????? API ?????????????????????
     * @throws MissionTypeNotMappingException ?????????????????????
     */
    private void checkMissionTypeAccept(MissionQuestionConfigDTO.MissionType exceptMissionType,
                                        MissionQuestionConfigDTO.MissionType actualMissionType)
            throws MissionTypeNotMappingException {
        if (!exceptMissionType.equals(actualMissionType)) {
            throw new MissionTypeNotMappingException(exceptMissionType, actualMissionType);
        }
    }

    /**
     * ????????????????????????????????????
     *
     * @param user      ???????????????
     * @param missionId mission id
     * @param imageId   image ?????????
     * @return
     */
    private String getSaveImageFileName(UserModel user, int missionId, int imageId) {
        return String.format("%s_%s_%s_%s", missionId, user.getEnglishName(), imageId, new SimpleDateFormat("HHmmss").format(new Date()));
    }

    /**
     * ????????????
     *
     * @param base64File ?????? Base64 ??????
     * @param fileName   ????????????????????????????????????????????????
     * @return
     * @throws IOException
     * @throws UnknownImageFormatException
     */
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

    /**
     * ????????????????????????????????????????????????
     */
    public void clearAllMissionAnswerData() {
        missionImageRepository.deleteAll();
        missionAnswerRepository.deleteAll();
    }

    public static MissionAnswerStatus getMissionAnswerStatus() {
        return missionAnswerStatus;
    }

    public static void setMissionAnswerStatus(MissionAnswerStatus missionAnswerStatus) {
        MissionService.missionAnswerStatus = missionAnswerStatus;
    }
}
