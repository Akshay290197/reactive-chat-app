package com.chat.reactive.dto.response;

import com.chat.reactive.enums.UserStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserResponse {
    private Long id;

    private String username;

    private String email;

    private String displayName;

    private String profilePicture;

    private UserStatus status;

    private LocalDateTime lastSeen;

}
