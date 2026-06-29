package com.chat.reactive.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private Long id;

    private String username;

    private String email;

    private String displayName;

    private String accessToken;

    private String tokenType;

}
