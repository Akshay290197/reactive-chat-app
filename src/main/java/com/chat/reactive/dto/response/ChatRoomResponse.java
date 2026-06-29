package com.chat.reactive.dto.response;

import com.chat.reactive.enums.ChatRoomType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatRoomResponse {
    private Long id;

    private String name;

    private String description;

    private ChatRoomType type;

    private Long createdBy;

    private String imageUrl;

    private Boolean isActive;
}
