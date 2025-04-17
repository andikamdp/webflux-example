package id.co.practice.webflux.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ValidationErrorResponseDto {
    private String code;
    private String message;

}
