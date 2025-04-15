package id.co.practice.webflux.controller;

import id.co.practice.webflux.constant.LogTemplate;
import id.co.practice.webflux.dto.response.GenericResponseDto;
import id.co.practice.webflux.service.impl.HolidayServiceImpl;
import id.co.practice.webflux.dto.request.HolidayDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("api/v1/holiday/data")
@RequiredArgsConstructor
public class HolidayController {
    
    private final HolidayServiceImpl holidayService;

    @PostMapping
    public Mono<ResponseEntity<GenericResponseDto<HolidayDto>>> saveHoliday(ServerHttpRequest serverHttpRequest, @RequestBody HolidayDto holidayDto) {
        log.info(LogTemplate.CONTROLLER_REQUEST_LOG_INFO, serverHttpRequest.getMethod(), serverHttpRequest.getPath(), holidayDto);
        
        return this.holidayService.save(holidayDto)
                .map(holiday -> {
                    GenericResponseDto<HolidayDto> responseData = GenericResponseDto.<HolidayDto>builder()
                            .code("00")
                            .message("ok")
                            .data(holiday)
                            .build();
                    return ResponseEntity.ok(responseData);
                })
                .doOnNext(response -> log.info(LogTemplate.CONTROLLER_RESPONSE_LOG_INFO, response, HttpStatus.OK));
    }
    
    @GetMapping("{id}")
    public Mono<ResponseEntity<GenericResponseDto<HolidayDto>>> getHoliday(ServerHttpRequest serverHttpRequest, @PathVariable UUID id) {
        log.info(LogTemplate.CONTROLLER_REQUEST_LOG_INFO, serverHttpRequest.getMethod(), serverHttpRequest.getPath(), id);
        
        return this.holidayService.findById(id)
                .map(holiday -> {
                    GenericResponseDto<HolidayDto> responseData = GenericResponseDto.<HolidayDto>builder()
                            .code("00")
                            .message("ok")
                            .data(holiday)
                            .build();
                    return ResponseEntity.ok(responseData);
                })
                .doOnNext(response -> log.info(LogTemplate.CONTROLLER_RESPONSE_LOG_INFO, response, HttpStatus.OK));
    }

    @GetMapping
    public Mono<ResponseEntity<List<HolidayDto>>> getHoliday(ServerHttpRequest serverHttpRequest, @RequestParam("page") int page, @RequestParam("size") int size) {
        log.info(LogTemplate.CONTROLLER_REQUEST_LOG_INFO, serverHttpRequest.getMethod(), serverHttpRequest.getPath(), "page : {}, size : {}", page, size);

        return this.holidayService.findAllPaged(PageRequest.of(page, size))
                .collectList()
                .map(holiday -> {

                    return ResponseEntity.ok(holiday);
                })
                .doOnNext(response -> log.info(LogTemplate.CONTROLLER_RESPONSE_LOG_INFO, response, HttpStatus.OK));
    }
    
    @PutMapping("{id}")
    public Mono<ResponseEntity<GenericResponseDto<HolidayDto>>> updateHoliday(ServerHttpRequest serverHttpRequest, @PathVariable UUID id, @RequestBody HolidayDto holidayDto) {
        log.info("Request Path : {} | Request Body : {} ", serverHttpRequest.getPath(), holidayDto);
        
        return this.holidayService.update(id, holidayDto)
                .map(holiday -> {
                    GenericResponseDto<HolidayDto> responseData = GenericResponseDto.<HolidayDto>builder()
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
        
        return this.holidayService.delete(id)
                .then(Mono.just(ResponseEntity.ok(GenericResponseDto.<Void>builder()
                        .code("00")
                        .message("ok")
                        .build())))
                .doOnNext(response -> log.info(LogTemplate.CONTROLLER_RESPONSE_LOG_INFO, response, HttpStatus.OK));
    }


    
}
