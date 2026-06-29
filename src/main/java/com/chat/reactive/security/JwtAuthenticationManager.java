package com.chat.reactive.security;

import com.chat.reactive.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationManager implements ReactiveAuthenticationManager {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();
        String email = jwtService.extractUsername(token);
        return userRepository.findByEmail(email)
                .flatMap(user -> {
                    if (!jwtService.isTokenValid(token, user)) {
                        return Mono.error(new BadCredentialsException("Invalid JWT Token"));
                    }

                    return Mono.just(
                            new UsernamePasswordAuthenticationToken(
                                    user,
                                    token,
                                    Collections.emptyList()
                            )
                    );

                });
    }
}
