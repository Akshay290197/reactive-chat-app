package com.chat.reactive.mapper;

import com.chat.reactive.dto.request.CreateChatRoomRequest;
import com.chat.reactive.dto.response.ChatRoomResponse;
import com.chat.reactive.entity.ChatRoom;

public interface ChatRoomMapper {
    ChatRoom toEntity(CreateChatRoomRequest request);

    ChatRoomResponse toResponse(ChatRoom room);
}
