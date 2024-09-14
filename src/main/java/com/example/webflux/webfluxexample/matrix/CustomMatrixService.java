package com.example.webflux.webfluxexample.matrix;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomMatrixService {
    private final Counter requestCounter;

    @Autowired
    public CustomMatrixService(MeterRegistry registry) {
        this.requestCounter = registry.counter("myapp_requests_total", "type", "http");
    }

    public void handleRequest() {
        // Increment the counter
        requestCounter.increment();

        // Simulate some business logic
        System.out.println("Handling request...");
    }
}
