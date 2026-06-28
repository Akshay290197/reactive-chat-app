package com.chat.reactive.mapper.impl;

import com.chat.reactive.dto.request.CreateUserRequest;
import com.chat.reactive.dto.response.UserResponse;
import com.chat.reactive.entity.User;
import com.chat.reactive.mapper.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public User toEntity(CreateUserRequest request) {

        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setDisplayName(request.getDisplayName());
        user.setProfilePicture(request.getProfilePicture());
        user.setStatus(request.getStatus());

        return user;
    }

    @Override
    public UserResponse toResponse(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .displayName(user.getDisplayName())
                .profilePicture(user.getProfilePicture())
                .status(user.getStatus())
                .lastSeen(user.getLastSeen())
                .build();
    }
}
