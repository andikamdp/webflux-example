//package com.example.webflux.webfluxexample.controller;
//
//import id.co.practice.circuitbreaker.dto.registration.RegisterAccountRequest;
//import id.co.practice.circuitbreaker.dto.registration.RegisterAccountResponse;
//import id.co.practice.circuitbreaker.proxy.ValidatorClient;
//import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
//import io.github.resilience4j.circuitbreaker.CircuitBreaker;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.security.SecureRandom;
//import java.util.UUID;
//import java.util.concurrent.TimeUnit;
//
//
//@Slf4j
//@RestController
//@RequestMapping("api/v1")
//@RequiredArgsConstructor
//public class MainController {
//    private final SecureRandom rand = new SecureRandom();
//    private final ValidatorClient validatorClient;
//    private final CircuitBreaker circuitBreaker;
//    private final String[] key = {"6502c69b-4678-4c20-9bbc-238633037f52", "e5364dc0-b069-4b79-8c4a-84b75119d7a7", "931de3d5-2df6-4ac0-a6e4-118d166b9b49"};
//
//    @PostMapping("register-account")
//    public ResponseEntity<RegisterAccountResponse> register(
//            ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse,
//            @RequestBody RegisterAccountRequest payload
//    ) throws InterruptedException {
//        log.info("{} {} register account : {}", httpRequest.getMethod(), httpRequest.getPathInfo(), payload);
//        for (int i = 0; i < 10000; i++) {
//            try {
//                circuitBreaker.decorateSupplier(() -> validatorClient.validateApi(500)).get();
//            } catch (CallNotPermittedException e) {
//                log.error("{} {}", i, e.getMessage());
//                TimeUnit.SECONDS.sleep(1);
//            } catch (Exception e) {
//                log.error("{} {}", i, e.getMessage());
//            }
//            log.info(circuitBreaker.getState().name());
//            log.info("getFailureRate : " + circuitBreaker.getMetrics().getFailureRate());
//            log.info("getSlowCallRate : " + circuitBreaker.getMetrics().getSlowCallRate());
//            log.info("getNumberOfSlowCalls : " + circuitBreaker.getMetrics().getNumberOfSlowCalls());
//            log.info("getNumberOfFailedCalls : " + circuitBreaker.getMetrics().getNumberOfFailedCalls());
//            log.info("getNumberOfSlowSuccessfulCalls : " + circuitBreaker.getMetrics().getNumberOfSlowSuccessfulCalls());
//            log.info("getNumberOfSlowFailedCalls : " + circuitBreaker.getMetrics().getNumberOfSlowFailedCalls());
//            log.info("getNumberOfBufferedCalls : " + circuitBreaker.getMetrics().getNumberOfBufferedCalls());
//            log.info("getNumberOfNotPermittedCalls : " + circuitBreaker.getMetrics().getNumberOfNotPermittedCalls());
//            log.info("getNumberOfSuccessfulCalls : " + circuitBreaker.getMetrics().getNumberOfSuccessfulCalls());
//
//        }
//
//        return ResponseEntity
//                .status(200)
//                .body(
//                        RegisterAccountResponse.builder()
//                                .registrationKey(UUID.randomUUID())
//                                .build()
//                );
//    }
//
//    public int randInt(int min, int max) {
//        return rand.nextInt((max - min) + 1) + min;
//    }
//}
