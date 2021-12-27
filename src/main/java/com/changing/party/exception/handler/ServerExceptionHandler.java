package com.changing.party.exception.handler;

import com.changing.party.exception.AnswerBinaryNotOpenException;
import com.changing.party.exception.UserIdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class ServerExceptionHandler {

    @ExceptionHandler(UserIdNotFoundException.class)
    public Object userIdNotFoundExceptionHandler(UserIdNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public Object userIdNotFoundExceptionHandler(UsernameNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
    }

    @ExceptionHandler(AnswerBinaryNotOpenException.class)
    public Object answerBinaryNotOpenExceptionHandler(AnswerBinaryNotOpenException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object constraintViolationExceptionHandler(MethodArgumentNotValidException exception) {
        String exceptionMessage = "";
        Optional<FieldError> fieldError = Optional.ofNullable(exception.getFieldError());
        if (fieldError.isPresent()) {
            exceptionMessage = fieldError.get().getDefaultMessage();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionMessage);
    }
}
