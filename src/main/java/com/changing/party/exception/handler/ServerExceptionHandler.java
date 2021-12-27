package com.changing.party.exception.handler;

import com.changing.party.exception.AnswerBinaryNotOpenException;
import com.changing.party.exception.UserIdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
    public Object userIdNotFoundExceptionHandler(AnswerBinaryNotOpenException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object ConstraintViolationExceptionHandler(MethodArgumentNotValidException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(Exception.class)
    public Object ConstraintViolationExceptionHandler(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST);
    }
}
