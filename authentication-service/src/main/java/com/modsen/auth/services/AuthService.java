package com.modsen.auth.services;

import com.modsen.auth.dtos.AuthRequest;
import com.modsen.auth.dtos.AuthResponse;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;

    public AuthResponse register(AuthRequest request) {
        String accessToken = jwtUtil.generate(request.getName(), request.getSurname());
        return new AuthResponse(accessToken);
    }

}