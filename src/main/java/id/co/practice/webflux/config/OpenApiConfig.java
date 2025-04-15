package id.co.practice.webflux.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                        .title("Reactive Spring Boot API")
                        .version("1.0")
                        .description("API documentation for the reactive Spring Boot application")
                ).servers(
                        List.of(
                        new Server().url("http://localhost:8081").description("Local Server"),
                        new Server().url("https://api.example.com").description("Production Server"))
                )
                ;
    }

    @Bean
    public OpenApiCustomizer globalHeaderOpenApiCustomiser() {
        return openApi -> openApi.getPaths().values().forEach(pathItem ->
                pathItem.readOperations().forEach(operation -> {
                    operation.addParametersItem(new Parameter()
                            .name("Accept-Language")
                            .description("Custom header for all APIs")
                            .required(false)
                            .schema(new StringSchema())
                            .in("header")
                    );
                    operation.addParametersItem(new Parameter()
                            .name("locale")
                            .description("Custom header for all APIs")
                            .required(false)
                            .schema(new StringSchema())
                            .in("header")
                    );
                    operation.addParametersItem(new Parameter()
                            .name("X-Trace-Id")
                            .description("Custom header for all APIs")
                            .required(false)
                            .schema(new StringSchema())
                            .in("header")
                    );
                })
        );
    }
}
