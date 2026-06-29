package com.chat.reactive.service.interfaces;

import com.chat.reactive.dto.request.CreateChatRoomRequest;
import com.chat.reactive.dto.response.ChatRoomResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ChatRoomService {
    Mono<ChatRoomResponse> createChatRoom(CreateChatRoomRequest request);

    Flux<ChatRoomResponse> getAllChatRooms();

    Mono<ChatRoomResponse> getChatRoomById(Long id);

    Mono<Void> joinRoom(Long roomId);
}
