package com.example.webflux.webfluxexample.controller;

import com.example.webflux.webfluxexample.dto.validate.ResponseDto;
import com.example.webflux.webfluxexample.util.DateTimeUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@Slf4j
@RestController
@RequestMapping("api/v1/holiday")
@RequiredArgsConstructor
public class HolidayController {
    private final HolidayDateDao dao;

    @PostMapping
    public ResponseEntity<ResponseDto> saveHoliday(HttpServletRequest request, HttpServletResponse response, @RequestBody HolidayDateDto payload) {
        log.info("Request Path : {} | Request Body : {} ", request.getRequestURI(), payload);
        this.dao.save(payload);
        ResponseDto responseData = ResponseDto.builder()
                .code("00")
                .message("ok")
                .build();

        log.info("Response Body : {} | Response status {} ", responseData, HttpStatus.OK);

        return ResponseEntity.ok(responseData);
    }

    @GetMapping("is-holiday")
    public ResponseEntity<ResponseDto> getRate(HttpServletRequest request, HttpServletResponse response, @RequestParam String date) throws NoHolidayFoundException {
        log.info("Request Path : {} | Request Body : {} ", request.getRequestURI(), date);
        ZonedDateTime holidayDate = DateTimeUtil.convertStringToZonedDatetime(date);
        boolean isHoliday = this.dao.findByHolidayDate(holidayDate);
        ResponseDto responseData = ResponseDto.builder()
                .code("00")
                .message("ok")
                .data(isHoliday)
                .build();
        log.info("Response Body : {} | Response status {} ", responseData, HttpStatus.OK);

        return ResponseEntity.ok(responseData);
    }
}
