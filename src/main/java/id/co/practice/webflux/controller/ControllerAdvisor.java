package id.co.practice.webflux.controller;

import id.co.practice.webflux.exception.GeneralErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

@Slf4j
@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> generalExceptionHandle(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, Exception exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("General Error Exception");
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<Object> webExchangeBindExceptionHandle(ServerHttpRequest  serverHttpRequest, ServerHttpResponse serverHttpResponse, WebExchangeBindException exception) {
        log.error(exception.getAllErrors().toString(), exception);
        return ResponseEntity.status(400).body(exception.getAllErrors());
    }
    @ExceptionHandler(GeneralErrorException.class)
    public ResponseEntity<Object> genericExceptionHandle(ServerHttpRequest  serverHttpRequest, ServerHttpResponse serverHttpResponse, GeneralErrorException exception) {
        log.error(exception.getData().toString(), exception);
        return ResponseEntity.status(exception.getError().getHttpCode()).body(exception.getData());
    }


}
