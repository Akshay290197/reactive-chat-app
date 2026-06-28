package com.chat.reactive.mapper;

import com.chat.reactive.dto.request.CreateUserRequest;
import com.chat.reactive.dto.response.UserResponse;
import com.chat.reactive.entity.User;

public interface UserMapper {
    User toEntity(CreateUserRequest request);

    UserResponse toResponse(User user);
}
