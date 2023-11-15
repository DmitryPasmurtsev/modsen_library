package com.modsen.books;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Test Task for Modsen Company",
                version = "1.0.0",
                description = "Web API для CRUD операций над книгами в библиотеке",
                contact = @Contact(
                        name = "Dmitry Pasmurtsev",
                        email = "pasmurtsevd@gmail.com"
                )
        )
)
public class BooksServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BooksServiceApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }
}
