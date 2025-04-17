package id.co.practice.webflux.exception;

import id.co.practice.webflux.dto.GeneralExceptionData;
import id.co.practice.webflux.dto.response.ValidationErrorResponseDto;
import id.co.practice.webflux.util.DateTimeUtil;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.List;

@Getter
@ToString
public class ValidationErrorException extends GeneralErrorException {

    private List<ValidationErrorResponseDto> validationErrorResponseDtoList;

    public ValidationErrorException(String exceptionMessage, List<ValidationErrorResponseDto> validationErrorResponseDtoList) {
        super(exceptionMessage, ExceptionType.INVALID_USER_INPUT_ERROR,validationErrorResponseDtoList);
        this.validationErrorResponseDtoList = validationErrorResponseDtoList;
    }
}
