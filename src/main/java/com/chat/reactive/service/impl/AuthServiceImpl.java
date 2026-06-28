package com.chat.reactive.service.impl;

import com.chat.reactive.dto.request.LoginRequest;
import com.chat.reactive.dto.response.LoginResponse;
import com.chat.reactive.exception.InvalidCredentialsException;
import com.chat.reactive.repository.UserRepository;
import com.chat.reactive.service.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Mono<LoginResponse> login(LoginRequest request) {
        return userRepository.findByEmail(request.getEmail())
                .switchIfEmpty(
                        Mono.error(new InvalidCredentialsException("Invalid email or password"))
                )
                .flatMap(user -> {

                    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                        return Mono.error(new InvalidCredentialsException("Invalid email or password"));
                    }

                    return Mono.just(
                            LoginResponse.builder()
                                    .id(user.getId())
                                    .username(user.getUsername())
                                    .email(user.getEmail())
                                    .displayName(user.getDisplayName())
                                    .message("Login Successful")
                                    .build()
                    );
                });
    }
}
