package com.example.webflux.webfluxexample.dto.registration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegisterAccountRequest {
    private String name;
    private String account;
    private String address;
}
