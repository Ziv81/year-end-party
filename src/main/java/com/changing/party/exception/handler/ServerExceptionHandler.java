package com.changing.party.exception.handler;

import com.changing.party.exception.UserIdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.Field;

@ControllerAdvice
public class ServerExceptionHandler {

    @ExceptionHandler(UserIdNotFoundException.class)
    public Object userIdNotFoundExceptionHandler(UserIdNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object ConstraintViolationExceptionHandler(MethodArgumentNotValidException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getFieldError().getDefaultMessage());
    }
}
