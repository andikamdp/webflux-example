package id.co.practice.webflux.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingUtil {

    public static void logRequest(String requestId, String method, String uri) {
        log.info("Request ID: {}, Method: {}, URI: {}", requestId, method, uri);
    }

    public static void logResponse(String requestId, String response) {
        log.info("Request ID: {}, Response: {}", requestId, response);
    }

    public static void logError(String requestId, String errorMessage) {
        log.error("Request ID: {}, Error: {}", requestId, errorMessage);
    }
}
