package com.example.webflux.webfluxexample.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum ExceptionType {
    GENERAL_ERROR("general_error", HttpStatus.INTERNAL_SERVER_ERROR),
    NO_DATA_FOUND("no_data_found", HttpStatus.BAD_REQUEST),
    NO_HOLIDAY_FOUND("no_holiday_found", HttpStatus.BAD_REQUEST);

    private final String messageCode;
    private final HttpStatusCode httpCode;
}
