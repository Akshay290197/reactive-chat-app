package com.chat.reactive.service.interfaces;

import com.chat.reactive.dto.request.LoginRequest;
import com.chat.reactive.dto.response.LoginResponse;
import reactor.core.publisher.Mono;

public interface AuthService {
    Mono<LoginResponse> login(LoginRequest request);
}
