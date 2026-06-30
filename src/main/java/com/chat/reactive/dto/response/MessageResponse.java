package com.chat.reactive.dto.response;

import com.chat.reactive.enums.MessageStatus;
import com.chat.reactive.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse {
    private Long id;

    private Long roomId;

    private Long senderId;

    private String content;

    private MessageType type;

    private MessageStatus status;

    private String fileUrl;

    private Boolean isEdited;

    private Boolean isDeleted;

    private LocalDateTime createdAt;
}
