package com.p2plending.userservice.service.impl;

import com.p2plending.userservice.dto.AuthResponse;
import com.p2plending.userservice.dto.LoginRequest;
import com.p2plending.userservice.model.User;
import com.p2plending.userservice.repository.UserRepository;
import com.p2plending.userservice.security.JwtService;
import com.p2plending.userservice.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String jwtToken = jwtService.generateToken(user.getEmail());
        return new AuthResponse(jwtToken);
    }
}
