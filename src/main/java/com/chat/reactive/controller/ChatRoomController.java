package com.chat.reactive.controller;

import com.chat.reactive.dto.request.CreateChatRoomRequest;
import com.chat.reactive.dto.response.ChatRoomResponse;
import com.chat.reactive.service.interfaces.ChatRoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/chat-rooms")
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    @PostMapping
    public Mono<ChatRoomResponse> createChatRoom(
            @Valid @RequestBody CreateChatRoomRequest request) {

        return chatRoomService.createChatRoom(request);
    }

    @GetMapping
    public Flux<ChatRoomResponse> getAllChatRooms() {

        return chatRoomService.getAllChatRooms();
    }

    @GetMapping("/{id}")
    public Mono<ChatRoomResponse> getChatRoomById(
            @PathVariable Long id) {

        return chatRoomService.getChatRoomById(id);
    }

    @PostMapping("/{roomId}/join")
    public Mono<Void> joinRoom(@PathVariable Long roomId) {

        return chatRoomService.joinRoom(roomId);
    }
}
