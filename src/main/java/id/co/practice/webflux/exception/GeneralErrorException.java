package id.co.practice.webflux.exception;

import id.co.practice.webflux.dto.GeneralExceptionData;
import id.co.practice.webflux.util.DateTimeUtil;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GeneralErrorException extends Exception {
    private final ExceptionType error;
    private final transient GeneralExceptionData<?> data;

    public GeneralErrorException(String exceptionMessage) {
        super(exceptionMessage);
        this.error = ExceptionType.GENERAL_ERROR;
        this.data = GeneralExceptionData.builder()
                .errorData(null)
                .errorCode(this.error.getMessageCode())
                .dateTime(DateTimeUtil.getCurrentDateFormat())
                .build();
    }

    public GeneralErrorException(String exceptionMessage, Object errorData) {
        super(exceptionMessage);
        this.error = ExceptionType.GENERAL_ERROR;
        this.data = GeneralExceptionData.builder()
                .errorData(errorData)
                .errorCode(this.error.getMessageCode())
                .dateTime(DateTimeUtil.getCurrentDateFormat())
                .build();
    }

    public GeneralErrorException(String exceptionMessage, ExceptionType errorInfo) {
        super(exceptionMessage);
        this.error = errorInfo;
        this.data = GeneralExceptionData.builder()
                .errorData(null)
                .errorCode(this.error.getMessageCode())
                .dateTime(DateTimeUtil.getCurrentDateFormat())
                .build();
    }

    public GeneralErrorException(String exceptionMessage, ExceptionType errorInfo, Object errorData) {
        super(exceptionMessage);
        this.error = errorInfo;
        this.data = GeneralExceptionData.builder()
                .errorData(errorData)
                .errorCode(this.error.getMessageCode())
                .dateTime(DateTimeUtil.getCurrentDateFormat())
                .build();
    }
}
