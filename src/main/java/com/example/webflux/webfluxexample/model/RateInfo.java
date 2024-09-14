package com.example.webflux.webfluxexample.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(schema = "param", name = "tbl_param")
@Getter
@Setter
public class RateInfo {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2")
    private UUID id;
    private String currency;
    private BigDecimal buyRate;
    private BigDecimal sellRate;
    private ZonedDateTime registeredDate;
}
