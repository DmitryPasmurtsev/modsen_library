package com.modsen.auth.controllers;

import com.modsen.auth.dtos.AuthRequest;
import com.modsen.auth.dtos.AuthResponse;
import com.modsen.auth.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Tag(name = "Аутентификационный контроллер",description = "Предназначен для получения JWT токена")
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/auth")
    @Operation(
            summary = "Получение JWT токена"
    )
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }


}