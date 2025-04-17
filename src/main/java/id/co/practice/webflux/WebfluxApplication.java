package id.co.practice.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Hooks;

/*
 * TODO
 *  1. DONE create a CRUD API about holiday date
 *  2. DONE create custom error message
 *  4. DONE create global error handler
 *  3. DONE create trace id
 *  5. create unit test
 *  6. create a new service to have integration
 *  7. find out implement taceId and spanId acrose microservices
 * */

@SpringBootApplication
public class WebfluxApplication {

    public static void main(String[] args) {
        String data = "test";
        data.strip();
        SpringApplication.run(WebfluxApplication.class, args);
        Hooks.enableAutomaticContextPropagation();
    }

}