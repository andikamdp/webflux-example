package com.example.webflux.webfluxexample.dto.registration;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
public class RegisterAccountResponse {
    private UUID registrationKey;
}
