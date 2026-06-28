package com.chat.reactive.security;

import com.chat.reactive.entity.User;

public interface JwtService {
    String generateToken(User user);

    String extractUsername(String token);

    boolean isTokenValid(String token, User user);
}
