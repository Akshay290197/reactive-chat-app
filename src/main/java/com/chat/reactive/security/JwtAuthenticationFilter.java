package com.chat.reactive.security;

import com.chat.reactive.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements WebFilter {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String authHeader = exchange.getRequest()
                .getHeaders()
                .getFirst("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return chain.filter(exchange);
        }

        String token = authHeader.substring(7);
        String email = jwtService.extractUsername(token);

        return userRepository.findByEmail(email)
                .flatMap(user -> {
                    if (!jwtService.isTokenValid(token, user)) {
                        if (!jwtService.isTokenValid(token, user)) {
                            return Mono.error(new BadCredentialsException("Invalid JWT Token"));
                        }
                    }

                    return chain.filter(exchange);

                })
                .switchIfEmpty(chain.filter(exchange));
    }
}
