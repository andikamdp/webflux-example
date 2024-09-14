package com.example.webflux.webfluxexample.exception;

import com.example.webflux.webfluxexample.dto.GeneralExceptionData;
import com.example.webflux.webfluxexample.util.DateTimeUtil;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

@Getter
@ToString
public class GeneralErrorException extends Exception {
    private final ExceptionType error;
    private final transient GeneralExceptionData<?> data;

    public GeneralErrorException(MessageSource messageSource) {
        super(messageSource.getMessage(ExceptionType.GENERAL_ERROR.getMessageCode(), null, LocaleContextHolder.getLocale()));
        this.error = ExceptionType.GENERAL_ERROR;
        this.data = GeneralExceptionData.builder()
                .errorData(null)
                .errorCode(this.error.getMessageCode())
                .errorMessage(messageSource.getMessage(this.error.getMessageCode(), null, LocaleContextHolder.getLocale()))
                .dateTime(DateTimeUtil.getCurrentDateFormat())
                .build();
    }

    public GeneralErrorException(MessageSource messageSource, Object errorData) {
        super(messageSource.getMessage(ExceptionType.GENERAL_ERROR.getMessageCode(), null, LocaleContextHolder.getLocale()));
        this.error = ExceptionType.GENERAL_ERROR;
        this.data = GeneralExceptionData.builder()
                .errorData(errorData)
                .errorCode(this.error.getMessageCode())
                .errorMessage(messageSource.getMessage(this.error.getMessageCode(), null, LocaleContextHolder.getLocale()))
                .dateTime(DateTimeUtil.getCurrentDateFormat())
                .build();
    }

    public GeneralErrorException(MessageSource messageSource, ExceptionType errorInfo) {
        super(messageSource.getMessage(ExceptionType.GENERAL_ERROR.getMessageCode(), null, LocaleContextHolder.getLocale()));
        this.error = errorInfo;
        this.data = GeneralExceptionData.builder()
                .errorData(null)
                .errorCode(this.error.getMessageCode())
                .errorMessage(messageSource.getMessage(this.error.getMessageCode(), null, LocaleContextHolder.getLocale()))
                .dateTime(DateTimeUtil.getCurrentDateFormat())
                .build();
    }

    public GeneralErrorException(MessageSource messageSource, ExceptionType errorInfo, Object errorData) {
        super(messageSource.getMessage(ExceptionType.GENERAL_ERROR.getMessageCode(), null, LocaleContextHolder.getLocale()));
        this.error = errorInfo;
        this.data = GeneralExceptionData.builder()
                .errorData(errorData)
                .errorCode(this.error.getMessageCode())
                .errorMessage(messageSource.getMessage(this.error.getMessageCode(), null, LocaleContextHolder.getLocale()))
                .dateTime(DateTimeUtil.getCurrentDateFormat())
                .build();
    }
}
