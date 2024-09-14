package com.example.webflux.webfluxexample.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(schema = "param", name = "tbl_holiday")
@Getter
@Setter
public class HolidayDate {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2")
    private UUID id;
    private ZonedDateTime date;
    private String description;
    private int status;
    private ZonedDateTime createdDate;
    private ZonedDateTime updatedDate;
}
