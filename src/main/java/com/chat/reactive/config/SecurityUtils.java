package com.chat.reactive.config;

import com.chat.reactive.entity.User;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import reactor.core.publisher.Mono;

public final class SecurityUtils {
    private SecurityUtils() {
    }

    public static Mono<User> getCurrentUser() {

        return ReactiveSecurityContextHolder.getContext()
                .map(context -> (User) context.getAuthentication().getPrincipal());
    }
}

