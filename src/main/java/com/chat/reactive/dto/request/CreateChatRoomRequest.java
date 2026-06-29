package com.chat.reactive.dto.request;

import com.chat.reactive.enums.ChatRoomType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateChatRoomRequest {
    @NotBlank(message = "Room name is required")
    private String name;

    private String description;

    @NotNull(message = "Room type is required")
    private ChatRoomType type;
}
