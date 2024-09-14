package com.example.webflux.webfluxexample.constant;

public class LogTemplate {
    @SuppressWarnings("unused")
    private LogTemplate() {
    }

    /* parameter index info
     * 1. http method
     * 2. endpoint
     * 3. masked payload
     * */
    public static final String CONTROLLER_REQUEST_LOG_INFO = "Request Http Method {} | Endpoint {} | Masked Payload";

    /* parameter index info
     * 1. payload in toString
     * */
    public static final String CONTROLLER_REQUEST_LOG_DEBUG = "Request Plain Payload {} ";

    /* parameter index info
     * 1. response body masked
     * 2. response status code
     * 3.
     * */
    public static final String CONTROLLER_RESPONSE_LOG_INFO = "Response Body Masked : {} | Response status {} ";

    /* parameter index info
     * 1. response body plain
     * */
    public static final String CONTROLLER_RESPONSE_LOG_DEBUG = "Response Body Plain {} ";

    /* parameter index info
     * 1. exception info in toString
     * */
    public static final String CONTROLLER_ADVISOR_LOG_ERROR = "Response Error Message {} ";

    /* parameter index info
     * 1. exception info in toString with data
     * */
    public static final String CONTROLLER_ADVISOR_LOG_DEBUG = "Response Error Body Plain {} ";

    /* parameter index info
     * 1. Method name
     * 2. process name
     * */
    public static final String SERVICE_PROCESS_MESSAGE = "Pocess on Method {} for {} ";

    /* parameter index info
     * 1. Method name
     * 2. process name
     * 3. plain body
     * */
    public static final String SERVICE_PROCESS_DEBUG_MESSAGE = "Pocess on Method {} for {} plain body {} ";

    /* parameter index info
     * 1. Method name
     * 2. error message
     * */
    public static final String SERVICE_ERROR_MESSAGE = "Pocess on Method {} Error {} ";
}
