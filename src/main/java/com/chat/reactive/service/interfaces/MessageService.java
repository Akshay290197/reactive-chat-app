package com.chat.reactive.service.interfaces;

import com.chat.reactive.dto.request.SendMessageRequest;
import com.chat.reactive.dto.response.MessageResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MessageService {
    Mono<MessageResponse> sendMessage(Long roomId, SendMessageRequest request);
    Flux<MessageResponse> getMessages(Long roomId);
}
