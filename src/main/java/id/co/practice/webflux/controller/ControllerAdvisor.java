package id.co.practice.webflux.controller;

import id.co.practice.webflux.dto.GeneralExceptionData;
import id.co.practice.webflux.exception.ExceptionType;
import id.co.practice.webflux.exception.GeneralErrorException;
import id.co.practice.webflux.util.DateTimeUtil;
import id.co.practice.webflux.util.MessageSourceUtil;
import id.co.practice.webflux.util.ReactiveLocaleUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

import java.util.Map;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvisor {

    private final ReactiveLocaleUtil reactiveLocaleUtil;

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<Object>> generalExceptionHandle(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, Exception exception) {
        log.error(exception.getMessage(), exception);
        return reactiveLocaleUtil.getLocale()
                .flatMap(locale -> {
                    Map<String, String> errorData = Map.of("errorCode", "500", "errorMessage", MessageSourceUtil.getErrorExceptionMessage(locale, "500"));

                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(GeneralExceptionData.builder()
                            .errorData(null)
                            .errorCode(ExceptionType.GENERAL_ERROR.getMessageCode())
                            .errorMessage(MessageSourceUtil.getErrorExceptionMessage(locale, ExceptionType.GENERAL_ERROR.getMessageCode()))
                            .dateTime(DateTimeUtil.getCurrentDateFormat())
                            .build()));
                });
    }

    @ExceptionHandler(GeneralErrorException.class)
    public Mono<ResponseEntity<Object>> genericExceptionHandle(ServerHttpRequest  serverHttpRequest, ServerHttpResponse serverHttpResponse, GeneralErrorException exception) {
        log.error(exception.getData().toString(), exception);
        return reactiveLocaleUtil.getLocale()
                .flatMap(locale -> {
                    exception.getData().setErrorMessage(MessageSourceUtil.getErrorExceptionMessage(locale, exception.getData().getErrorCode()));
                    ResponseEntity.status(exception.getError().getHttpCode()).body(exception.getData());
                    return Mono.just(ResponseEntity.status(exception.getError().getHttpCode()).body(exception.getData()));
                });
    }


}
