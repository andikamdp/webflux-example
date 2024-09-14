package com.example.webflux.webfluxexample.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;


@Getter
@Setter
public class HolidayDateDto {

    private UUID id;
    private ZonedDateTime date;
    private String description;
    private int status;
    private ZonedDateTime createdDate;
    private ZonedDateTime updatedDate;
}
