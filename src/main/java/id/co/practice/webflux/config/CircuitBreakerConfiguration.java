//package com.example.webflux.webfluxexample.config;
//
//import io.github.resilience4j.circuitbreaker.CircuitBreaker;
//import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.Duration;
//
//@Configuration
//public class CircuitBreakerConfiguration {
//
//    @Bean
//    public CircuitBreaker circuitBreaker() {
//        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
//                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
//                .slidingWindowSize(100)
//                .failureRateThreshold(60)
//                .slowCallRateThreshold(60)
//                .slowCallDurationThreshold(Duration.ofSeconds(120))
//                .minimumNumberOfCalls(50)
//                .permittedNumberOfCallsInHalfOpenState(50)
//                .maxWaitDurationInHalfOpenState(Duration.ofSeconds(60))
//                .automaticTransitionFromOpenToHalfOpenEnabled(true)
//                .waitDurationInOpenState(Duration.ofSeconds(120)) // Time to wait in the open state before transitioning to half-open
//                .ignoreExceptions()
//                .build();
//        return CircuitBreaker.of("sample-api", config);
//    }
//}
