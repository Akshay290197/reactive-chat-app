package com.chat.reactive.service.interfaces;

import com.chat.reactive.dto.request.CreateUserRequest;
import com.chat.reactive.dto.response.UserResponse;
import com.chat.reactive.entity.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<UserResponse> createUser(CreateUserRequest request);

    Mono<UserResponse> getUserById(Long id);

    Flux<UserResponse> getAllUsers();

    Mono<Void> deleteUser(Long id);
}
