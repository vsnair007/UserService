package com.mundackal.UserService.controller;

import com.mundackal.UserService.exception.SessionNotFoundException;
import com.mundackal.UserService.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalAdvice {
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity userNotFoundHandler(UserNotFoundException exception){
        return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = SessionNotFoundException.class)
    public ResponseEntity sessionNotFoundHandler(SessionNotFoundException exception){
        return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
