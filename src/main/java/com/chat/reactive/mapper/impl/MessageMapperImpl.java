package com.chat.reactive.mapper.impl;

import com.chat.reactive.dto.request.SendMessageRequest;
import com.chat.reactive.dto.response.MessageResponse;
import com.chat.reactive.entity.Message;
import com.chat.reactive.mapper.MessageMapper;
import org.springframework.stereotype.Component;

@Component
public class MessageMapperImpl implements MessageMapper {
    @Override
    public Message toEntity(SendMessageRequest request) {
        return Message.builder()
                .content(request.getContent())
                .type(request.getType())
                .build();
    }

    @Override
    public MessageResponse toResponse(Message message) {
        return MessageResponse.builder()
                .id(message.getId())
                .roomId(message.getRoomId())
                .senderId(message.getSenderId())
                .content(message.getContent())
                .type(message.getType())
                .status(message.getStatus())
                .fileUrl(message.getFileUrl())
                .isEdited(message.getIsEdited())
                .isDeleted(message.getIsDeleted())
                .createdAt(message.getCreatedAt())
                .build();
    }
}
