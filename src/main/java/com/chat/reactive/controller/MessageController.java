package com.chat.reactive.controller;

import com.chat.reactive.dto.request.SendMessageRequest;
import com.chat.reactive.dto.response.MessageResponse;
import com.chat.reactive.service.interfaces.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/chat-rooms")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @PostMapping("/{roomId}/messages")
    public Mono<MessageResponse> sendMessage(@PathVariable Long roomId, @Valid @RequestBody SendMessageRequest request) {
        return messageService.sendMessage(roomId, request);
    }

    @GetMapping("/{roomId}/messages")
    public Flux<MessageResponse> getMessages(@PathVariable Long roomId) {
        return messageService.getMessages(roomId);
    }
}
