package id.co.practice.webflux.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GeneralExceptionData<T> {
    private String dateTime;
    private String errorCode;
    private String errorMessage;
    private T errorData;

    @Override
    public String toString() {
        return "GenericExceptionData{" +
                "dateTime='" + dateTime + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }

    public String toStringData() {
        return "GenericExceptionData{" +
                "dateTime='" + dateTime + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", errorData=" + errorData +
                '}';
    }
}
