package io.luetzen.eventservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuration for OpenAPI documentation.
 */
@Configuration
public class OpenApiConfig {
    
    /**
     * Customize the OpenAPI documentation.
     *
     * @return the customized OpenAPI bean
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Event Service API")
                        .description("API for managing events related to Steam applications")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Luetzen.io")
                                .url("https://luetzen.io")
                                .email("info@luetzen.io"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Local development server")
                ));
    }
}