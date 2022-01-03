package com.changing.party.common.exception.handler;

import com.changing.party.common.ServerConstant;
import com.changing.party.common.exception.*;
import com.changing.party.response.Response;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

@ControllerAdvice
@Log4j2
public class ServerExceptionHandler {

    @ExceptionHandler(UserIdNotFoundException.class)
    public Object usernameNotFoundExceptionHandler(UserIdNotFoundException exception) {
        log.error("User id not found.", exception);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Response.builder()
                        .errorCode(ServerConstant.SERVER_FAIL_CODE)
                        .errorMessage("User id not found")
                        .build()
        );
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public Object usernameNotFoundExceptionHandler(UsernameNotFoundException exception) {
        log.error("User name not found.", exception);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Response.builder()
                        .errorCode(ServerConstant.SERVER_FAIL_CODE)
                        .errorMessage("User name not found")
                        .build()
        );
    }

    @ExceptionHandler(AnswerBinaryNotOpenException.class)
    public Object answerBinaryNotOpenExceptionHandler(AnswerBinaryNotOpenException exception) {
        log.error("Answer binary at binary close.", exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Response.builder()
                        .errorCode(ServerConstant.SERVER_FAIL_CODE)
                        .errorMessage("Binary not open to answer.")
                        .build()
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Object constraintViolationExceptionHandler(ConstraintViolationException exception) {
        log.error("Constrain violation exception", exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Response.builder()
                        .errorCode(ServerConstant.SERVER_FAIL_CODE)
                        .errorMessage(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
        String exceptionMessage = "";
        Optional<FieldError> fieldError = Optional.ofNullable(exception.getFieldError());
        if (fieldError.isPresent()) {
            exceptionMessage = fieldError.get().getDefaultMessage();
        }
        log.error("Method argument not valid exception", exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Response.builder()
                        .errorCode(ServerConstant.SERVER_FAIL_CODE)
                        .errorMessage(exceptionMessage)
                        .build());
    }

    @ExceptionHandler(GetUserRankException.class)
    public Object getUserRankExceptionHandler(GetUserRankException getUserRankException) {
        log.error("Get user rank exception", getUserRankException);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                Response.builder()
                        .errorCode(ServerConstant.SERVER_FAIL_CODE)
                        .errorMessage(getUserRankException.getMessage())
                        .build());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Object methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException exception) {
        log.error("Method argument type mismatch exception", exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Response.builder()
                        .errorCode(ServerConstant.SERVER_FAIL_CODE)
                        .errorMessage("Input type error check input type.")
                        .build());
    }

    @ExceptionHandler(MissionIDNotFoundException.class)
    public Object missionIDNotFoundExceptionHandler(MissionIDNotFoundException exception) {
        log.error("Mission id not found {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Response.builder()
                        .errorCode(ServerConstant.SERVER_FAIL_CODE)
                        .errorMessage("Mission id not found.")
                        .build());
    }

    @ExceptionHandler(MissionTypeNotMappingException.class)
    public Object missionTypeNotMappingExceptionHandler(MissionTypeNotMappingException exception) {
        log.error("Mission type not mapping {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Response.builder()
                        .errorCode(ServerConstant.SERVER_FAIL_CODE)
                        .errorMessage("Mission type not mapping. Check server mission config and url.")
                        .build());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Object httpMessageNotReadableException(HttpMessageNotReadableException exception) {
        log.error("Http message not readable exception", exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Response.builder()
                        .errorCode(ServerConstant.SERVER_FAIL_CODE)
                        .errorMessage("Http message not readable.")
                        .build());
    }

    @ExceptionHandler(UnknownImageFormatException.class)
    public Object unknownImageFormatExceptionHandler(UnknownImageFormatException exception) {
        log.error("Unknown image format exception. Image content {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Response.builder()
                        .errorCode(ServerConstant.SERVER_FAIL_CODE)
                        .errorMessage("Unknown image format exception.")
                        .build());
    }

    @ExceptionHandler(MissionAlreadyAnswerException.class)
    public Object missionAlreadyAnswerExceptionHandler(MissionAlreadyAnswerException exception) {
        log.error("Mission already answer.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Response.builder()
                        .errorCode(ServerConstant.SERVER_FAIL_CODE)
                        .errorMessage("Mission already answer.")
                        .build());
    }

    @ExceptionHandler(ImageIdNotFoundException.class)
    public Object imageIdNotFoundExceptionHandler(ImageIdNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Response.builder()
                        .errorCode(ServerConstant.SERVER_FAIL_CODE)
                        .errorMessage("Image id not exist.")
                        .build());
    }

    @ExceptionHandler(ImageStatusNotReview.class)
    public Object imageStatusNotReviewExceptionHandler(ImageStatusNotReview exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Response.builder()
                        .errorCode(ServerConstant.SERVER_FAIL_CODE)
                        .errorMessage("Image status not review.")
                        .build());
    }

    @ExceptionHandler(MissionAnswerImageListSizeNotAcceptException.class)
    public Object missionAnswerImageListSizeNotAcceptExceptionHandler(MissionAnswerImageListSizeNotAcceptException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Response.builder()
                        .errorCode(ServerConstant.SERVER_FAIL_CODE)
                        .errorMessage("Mission answer image list size not accept.")
                        .build());
    }

    @ExceptionHandler(Exception.class)
    public Object unCaughtException(Exception exception) {
        log.error("Uncaught exception", exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                Response.builder()
                        .errorCode(ServerConstant.SERVER_FAIL_CODE)
                        .errorMessage("Uncaught exception see log.")
                        .build());
    }

    @ExceptionHandler(Throwable.class)
    public Object throwableException(Throwable throwable) {
        log.error("Throwable exception", throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                Response.builder()
                        .errorCode(ServerConstant.SERVER_FAIL_CODE)
                        .errorMessage("Throwable exception see log.")
                        .build());
    }
}
