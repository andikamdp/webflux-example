package id.co.practice.webflux.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.ZonedDateTime;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class GenericResponseDto<T> {
    private String code;
    private String message;
    private ZonedDateTime responseTime;
    private T data;
}
