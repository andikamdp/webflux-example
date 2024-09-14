package com.example.webflux.webfluxexample.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
public class RateInfoDto {
    private UUID id;
    private String currency;
    private BigDecimal buyRate;
    private BigDecimal sellRate;
    private ZonedDateTime registeredDate;

    @Override
    public String toString() {
        return "RateInfoDto{" +
                "id=" + id +
                ", currency='" + currency + '\'' +
                ", buyRate=" + buyRate +
                ", sellRate=" + sellRate +
                ", registeredDate=" + registeredDate +
                '}';
    }
}
