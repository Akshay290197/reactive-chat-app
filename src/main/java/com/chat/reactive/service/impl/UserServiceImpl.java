package com.chat.reactive.service.impl;

import com.chat.reactive.dto.request.CreateUserRequest;
import com.chat.reactive.dto.response.UserResponse;
import com.chat.reactive.entity.User;
import com.chat.reactive.exception.EmailAlreadyExistsException;
import com.chat.reactive.exception.UserNotFoundException;
import com.chat.reactive.mapper.UserMapper;
import com.chat.reactive.repository.UserRepository;
import com.chat.reactive.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<UserResponse> createUser(CreateUserRequest request) {
        return userRepository.findByEmail(request.getEmail())
                .hasElement()
                .flatMap(exists -> {

                    if (exists) {
                        return Mono.error(new EmailAlreadyExistsException(request.getEmail()));
                    }

                    User user = userMapper.toEntity(request);
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    user.setCreatedAt(LocalDateTime.now());
                    user.setUpdatedAt(LocalDateTime.now());

                    return userRepository.save(user)
                            .map(userMapper::toResponse);
                });
    }

    @Override
    public Mono<UserResponse> getUserById(Long id) {
        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(new UserNotFoundException(id)))
                .map(userMapper::toResponse);
    }

    @Override
    public Flux<UserResponse> getAllUsers() {
        return userRepository.findAll().map(userMapper::toResponse);
    }

    @Override
    public Mono<Void> deleteUser(Long id) {
        return userRepository.deleteById(id);
    }
}
