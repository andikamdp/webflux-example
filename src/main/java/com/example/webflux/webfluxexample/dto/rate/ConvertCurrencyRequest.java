package com.example.webflux.webfluxexample.dto.rate;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConvertCurrencyRequest {
    private String sourceCurrency;
    private String destinationCurrency;
    private String type;
    private BigDecimal amount;
}