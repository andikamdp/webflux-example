package com.example.webflux.webfluxexample.controller;

import com.example.webflux.webfluxexample.dto.RateInfoDto;
import com.example.webflux.webfluxexample.dto.validate.ResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping("api/v1/rate")
@RequiredArgsConstructor
public class RateController {
    private final RateDao dao;

    @PostMapping
    public ResponseEntity<ResponseDto> saveRate(HttpServletRequest request, HttpServletResponse response, @RequestBody RateInfoDto payload) {
        log.info("Request Path : {} | Request Body : {} ", request.getRequestURI(), payload);
        this.dao.save(payload);

        ResponseDto responseData = ResponseDto.builder()
                .code("00")
                .message("ok")
                .build();
        log.info("Response Body : {} | Response status {} ", responseData, HttpStatus.OK);

        return ResponseEntity.ok(responseData);
    }

    @GetMapping("{currency}")
    public ResponseEntity<ResponseDto> getRate(HttpServletRequest request, HttpServletResponse response, @PathVariable(name = "currency") String currency) throws NoDataFoundException {
        log.info("Request Path : {} | Request Body : {} ", request.getRequestURI(), currency);
        this.dao.findByCurrency(currency);
        ResponseDto responseData = ResponseDto.builder()
                .code("00")
                .message("ok")
                .build();
        log.info("Response Body : {} | Response status {} ", responseData, HttpStatus.OK);

        return ResponseEntity.ok(responseData);
    }

    @PostMapping("convert")
    public ResponseEntity<ResponseDto> getRate(HttpServletRequest request, HttpServletResponse response, @RequestBody ConvertCurrencyRequest payload) throws NoDataFoundException {
        log.info("Request Path : {} | Request Body : {} ", request.getRequestURI(), payload);
        RateInfoDto rateInfo = this.dao.findByCurrency(payload.getDestinationCurrency());
        BigDecimal value = rateInfo.getBuyRate().multiply(payload.getAmount());
        ResponseDto responseData = ResponseDto.builder()
                .code("00")
                .message("ok")
                .data(value)
                .build();
        log.info("Response Body : {} | Response status {} ", responseData, HttpStatus.OK);

        return ResponseEntity.ok(responseData);
    }
}
