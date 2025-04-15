package id.co.practice.webflux;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Hooks;

/*
 * TODO
 *  1. DONE create a CRUD API about holiday date
 *  2. DONE create custom error message
 *  4. DONE create global error handler
 *  3. create trace id
 *  5. create unit test
 *  6. create a new service to have integration
 * */

@SpringBootApplication
public class WebfluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxApplication.class, args);
    }
    @PostConstruct
    public void init() {
        Hooks.enableAutomaticContextPropagation();
    }
}


///
//export GRAALVM_HOME="/Library/Java/JavaVirtualMachines/graalvm-jdk-17.0.12+8.1/Contents/Home"
//		export JAVA_HOME="$GRAALVM_HOME"
//		export PATH="$GRAALVM_HOME/bin:$PATH"
