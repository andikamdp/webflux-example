package com.example.webflux.webfluxexample.controller;

import com.example.webflux.webfluxexample.dto.validate.ResponseDto;
import com.example.webflux.webfluxexample.matrix.CustomMatrixService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class DummyAPIController {

    private final CustomMatrixService customMatrixService;

    @GetMapping("validate/{status}")
    public ResponseEntity<ResponseDto> validateDummyApi(HttpServletRequest request, HttpServletResponse response, @PathVariable(name = "status") int status) {
        log.info("Request Path : {} | Request Body : {} ", request.getRequestURI(), status);
        ResponseDto responseData = ResponseDto.builder()
                .code(String.valueOf(status))
                .message("Hello-please keep this message")
                .build();
        customMatrixService.handleRequest();
        log.info("Response Body : {} | Response status {} ", responseData, status);

        return ResponseEntity.status(status).body(
                responseData
        );
    }

}
