package com.example.webflux.webfluxexample.controller;

import com.example.webflux.webfluxexample.exception.GeneralErrorException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> generalExceptionHandle(HttpServletResponse httpServletResponse, Exception exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("General Error Exception");
    }

    @ExceptionHandler(GeneralErrorException.class)
    public ResponseEntity<Object> genericExceptionHandle(HttpServletResponse httpServletResponse, GeneralErrorException exception) {
        log.error(exception.getData().toString());
        return ResponseEntity.status(exception.getError().getHttpCode()).body(exception.getData());
    }


}
