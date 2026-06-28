package com.chat.reactive.security;

import com.chat.reactive.entity.User;

import java.util.Date;

public interface JwtService {
    String generateToken(User user);

    String extractUsername(String token);
    Date extractExpiration(String token);

    boolean isTokenValid(String token, User user);
}
