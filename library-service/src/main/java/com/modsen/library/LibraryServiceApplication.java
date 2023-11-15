package com.modsen.library;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Test Task for Modsen Company",
                version = "1.0.0",
                description = "Web API для учета свободных книг",
                contact = @Contact(
                        name = "Dmitry Pasmurtsev",
                        email = "pasmurtsevd@gmail.com"
                )
        )
)
public class LibraryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryServiceApplication.class, args);
    }
    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }
}
