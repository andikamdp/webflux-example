package com.example.webflux.webfluxexample.dto.validate;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class ResponseDto {
    private String code;
    private String message;
    private Object data;
}
