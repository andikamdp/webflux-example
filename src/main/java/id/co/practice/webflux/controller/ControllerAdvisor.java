package id.co.practice.webflux.controller;

import id.co.practice.webflux.config.ReactiveMessageInterpolator;
import id.co.practice.webflux.config.ReactiveRequestContextHolder;
import id.co.practice.webflux.exception.GeneralErrorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.i18n.LocaleContextResolver;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Locale;
import java.util.Map;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvisor {

    private final ReactiveMessageInterpolator messageInterpolator;
    private final LocaleContextResolver localeContextResolver; // Add this dependency


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> generalExceptionHandle(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, Exception exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("General Error Exception");
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<Object>> webExchangeBindExceptionHandle(ServerHttpRequest  serverHttpRequest, ServerHttpResponse serverHttpResponse, WebExchangeBindException exception) {
        log.error(exception.getAllErrors().toString(), exception);
        return Flux.fromIterable(exception.getFieldErrors())
                .flatMap(fieldError -> ReactiveRequestContextHolder.getServerWebExchange()
                        .map(exchange -> {
                            Locale locale = localeContextResolver.resolveLocaleContext(exchange).getLocale();
                            return locale;

                        })
                        .defaultIfEmpty(Locale.getDefault())
                        .map(locale -> messageInterpolator.interpolate(fieldError.getDefaultMessage(), null, locale))
                        .map(message -> Map.of(
                                "field", fieldError.getField(),
                                "rejectedValue", fieldError.getRejectedValue(),
                                "message", message
                        )))
                .collectList()
                .map(errors -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors));
    }
    @ExceptionHandler(GeneralErrorException.class)
    public ResponseEntity<Object> genericExceptionHandle(ServerHttpRequest  serverHttpRequest, ServerHttpResponse serverHttpResponse, GeneralErrorException exception) {
        log.error(exception.getData().toString(), exception);
        return ResponseEntity.status(exception.getError().getHttpCode()).body(exception.getData());
    }


}
