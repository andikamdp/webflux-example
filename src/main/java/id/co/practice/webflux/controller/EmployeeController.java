package id.co.practice.webflux.controller;

import id.co.practice.webflux.constant.LogTemplate;
import id.co.practice.webflux.dto.request.EmployeeDto;
import id.co.practice.webflux.dto.response.GenericResponseDto;
import id.co.practice.webflux.service.impl.EmployeeServiceImpl;
import id.co.practice.webflux.util.LoggingUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("api/v1/employee/data")
@RequiredArgsConstructor
public class EmployeeController {
    
    private final EmployeeServiceImpl employeeService;

    @PostMapping
    public Mono<ResponseEntity<GenericResponseDto<EmployeeDto>>> saveHoliday(ServerHttpRequest serverHttpRequest, @RequestBody @Valid EmployeeDto employeeDto) {
        log.info(LogTemplate.CONTROLLER_REQUEST_LOG_INFO, serverHttpRequest.getMethod(), serverHttpRequest.getPath(), employeeDto);
        LoggingUtil.logRequest(serverHttpRequest.getMethod().name(), serverHttpRequest.getPath().value(), employeeDto.toString());
        return this.employeeService.save(employeeDto)
                .doOnNext(employeeDto1 -> {log.info(LogTemplate.CONTROLLER_REQUEST_LOG_INFO.concat(" inside reactor"), serverHttpRequest.getMethod(), serverHttpRequest.getPath(), employeeDto);})
                .map(holiday -> {
                    GenericResponseDto<EmployeeDto> responseData = GenericResponseDto.<EmployeeDto>builder()
                            .code("00")
                            .message("ok")
                            .data(holiday)
                            .build();
                    return ResponseEntity.ok(responseData);
                })
                .doOnNext(response -> log.info(LogTemplate.CONTROLLER_RESPONSE_LOG_INFO, response, HttpStatus.OK));
    }
    
    @GetMapping("{id}")
    public Mono<ResponseEntity<GenericResponseDto<EmployeeDto>>> getHoliday(ServerHttpRequest serverHttpRequest, @PathVariable UUID id) {
        log.info(LogTemplate.CONTROLLER_REQUEST_LOG_INFO, serverHttpRequest.getMethod(), serverHttpRequest.getPath(), id);
        
        return this.employeeService.findById(id)
                .map(holiday -> {
                    GenericResponseDto<EmployeeDto> responseData = GenericResponseDto.<EmployeeDto>builder()
                            .code("00")
                            .message("ok")
                            .data(holiday)
                            .build();
                    return ResponseEntity.ok(responseData);
                })
                .doOnNext(response -> log.info(LogTemplate.CONTROLLER_RESPONSE_LOG_INFO, response, HttpStatus.OK));
    }

    @GetMapping
    public Mono<ResponseEntity<List<EmployeeDto>>> getHoliday(ServerHttpRequest serverHttpRequest, @RequestParam("page") int page, @RequestParam("size") int size) {
        log.info(LogTemplate.CONTROLLER_REQUEST_LOG_INFO, serverHttpRequest.getMethod(), serverHttpRequest.getPath(), "page : {}, size : {}", page, size);

        return this.employeeService.findAllPaged(PageRequest.of(page, size))
                .collectList()
                .map(holiday -> {
                    return ResponseEntity.ok(holiday);
                })
                .doOnNext(response -> log.info(LogTemplate.CONTROLLER_RESPONSE_LOG_INFO, response, HttpStatus.OK));
    }
    
    @PutMapping("{id}")
    public Mono<ResponseEntity<GenericResponseDto<EmployeeDto>>> updateHoliday(ServerHttpRequest serverHttpRequest, @PathVariable UUID id, @RequestBody @Valid EmployeeDto employeeDto) {
        log.info("Request Path : {} | Request Body : {} ", serverHttpRequest.getPath(), employeeDto);
        
        return this.employeeService.update(id, employeeDto)
                .map(holiday -> {
                    GenericResponseDto<EmployeeDto> responseData = GenericResponseDto.<EmployeeDto>builder()
                            .code("00")
                            .message("ok")
                            .data(holiday)
                            .build();
                    return ResponseEntity.ok(responseData);
                })
                .doOnNext(response -> log.info(LogTemplate.CONTROLLER_RESPONSE_LOG_INFO, response, HttpStatus.OK));
    }
    
    @DeleteMapping("{id}")
    public Mono<ResponseEntity<GenericResponseDto<Void>>> deleteHoliday(ServerHttpRequest serverHttpRequest, @PathVariable UUID id) {
        log.info(LogTemplate.CONTROLLER_REQUEST_LOG_INFO, serverHttpRequest.getMethod(), serverHttpRequest.getPath(), id);
        
        return this.employeeService.delete(id)
                .then(Mono.just(ResponseEntity.ok(GenericResponseDto.<Void>builder()
                        .code("00")
                        .message("ok")
                        .build())))
                .doOnNext(response -> log.info(LogTemplate.CONTROLLER_RESPONSE_LOG_INFO, response, HttpStatus.OK));
    }

}

/*
* 67ff640ea4adf37d
* 44b7a6aeba5d9770
* f62b50da931c5
* a68b9a0185101702
*
* */