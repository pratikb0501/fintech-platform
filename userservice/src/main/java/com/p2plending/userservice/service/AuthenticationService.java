package com.p2plending.userservice.service;

import com.p2plending.userservice.dto.AuthResponse;
import com.p2plending.userservice.dto.LoginRequest;

public interface AuthenticationService {
    AuthResponse login(LoginRequest request);
}
