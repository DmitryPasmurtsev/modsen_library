package com.modsen.auth;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Test Task for Modsen Company",
                version = "1.0.0",
                description = "Сервис для получения JWT токена",
                contact = @Contact(
                        name = "Dmitry Pasmurtsev",
                        email = "pasmurtsevd@gmail.com"
                )
        )
)
public class AuthenticationApp {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationApp.class, args);
    }

}
