package com.p2plending.userservice.service;

import com.p2plending.userservice.dto.AuthResponse;
import com.p2plending.userservice.dto.RegisterRequest;

public interface RegistrationService  {
    AuthResponse register(RegisterRequest request);
}
