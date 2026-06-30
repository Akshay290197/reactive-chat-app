package com.chat.reactive.mapper;

import com.chat.reactive.dto.request.SendMessageRequest;
import com.chat.reactive.dto.response.MessageResponse;
import com.chat.reactive.entity.Message;

public interface MessageMapper {
    Message toEntity(SendMessageRequest request);

    MessageResponse toResponse(Message message);
}
