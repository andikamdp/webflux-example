package id.co.practice.webflux.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum ExceptionType {
    INVALID_USER_INPUT_ERROR("invalid-user-input", HttpStatus.BAD_REQUEST),
    GENERAL_ERROR("general-error", HttpStatus.INTERNAL_SERVER_ERROR),
    NO_DATA_FOUND("no-data-found", HttpStatus.BAD_REQUEST),
    NO_HOLIDAY_FOUND("no-holiday-found", HttpStatus.BAD_REQUEST);

    private final String messageCode;
    private final HttpStatusCode httpCode;
}
