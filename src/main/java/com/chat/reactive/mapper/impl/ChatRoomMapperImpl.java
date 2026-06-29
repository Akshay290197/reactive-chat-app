package com.chat.reactive.mapper.impl;

import com.chat.reactive.dto.request.CreateChatRoomRequest;
import com.chat.reactive.dto.response.ChatRoomResponse;
import com.chat.reactive.entity.ChatRoom;
import com.chat.reactive.mapper.ChatRoomMapper;
import org.springframework.stereotype.Component;

@Component
public class ChatRoomMapperImpl implements ChatRoomMapper {
    @Override
    public ChatRoom toEntity(CreateChatRoomRequest request) {
        ChatRoom chatRoom = new ChatRoom();

        chatRoom.setName(request.getName());
        chatRoom.setDescription(request.getDescription());
        chatRoom.setType(request.getType());

        return chatRoom;
    }

    @Override
    public ChatRoomResponse toResponse(ChatRoom room) {
        return ChatRoomResponse.builder()
                .id(room.getId())
                .name(room.getName())
                .description(room.getDescription())
                .type(room.getType())
                .createdBy(room.getCreatedBy())
                .imageUrl(room.getImageUrl())
                .isActive(room.getIsActive())
                .build();
    }
}
