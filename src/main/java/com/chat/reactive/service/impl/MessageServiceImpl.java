package com.chat.reactive.service.impl;

import com.chat.reactive.config.SecurityUtils;
import com.chat.reactive.dto.request.SendMessageRequest;
import com.chat.reactive.dto.response.MessageResponse;
import com.chat.reactive.entity.Message;
import com.chat.reactive.enums.MessageStatus;
import com.chat.reactive.exception.ChatRoomNotFoundException;
import com.chat.reactive.exception.RoomAccessDeniedException;
import com.chat.reactive.mapper.MessageMapper;
import com.chat.reactive.repository.ChatRoomMemberRepository;
import com.chat.reactive.repository.ChatRoomRepository;
import com.chat.reactive.repository.MessageRepository;
import com.chat.reactive.service.interfaces.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomMemberRepository chatRoomMemberRepository;
    private final MessageMapper messageMapper;

    @Override
    public Mono<MessageResponse> sendMessage(Long roomId, SendMessageRequest request) {

        return SecurityUtils.getCurrentUser()
                .flatMap(user -> {

                    return chatRoomRepository.findById(roomId)
                            .switchIfEmpty(
                                    Mono.error(new ChatRoomNotFoundException(roomId))
                            )
                            .flatMap(chatRoom -> {

                                return chatRoomMemberRepository
                                        .findByRoomIdAndUserId(roomId, user.getId())
                                        .switchIfEmpty(
                                                Mono.error(new RoomAccessDeniedException())
                                        )
                                        .flatMap(member -> {

                                            Message message = messageMapper.toEntity(request);

                                            message.setRoomId(roomId);
                                            message.setSenderId(user.getId());

                                            message.setStatus(MessageStatus.SENT);

                                            message.setIsEdited(false);
                                            message.setIsDeleted(false);

                                            message.setCreatedAt(LocalDateTime.now());
                                            message.setUpdatedAt(LocalDateTime.now());

                                            return messageRepository.save(message).map(messageMapper::toResponse);

                                        });

                            });

                });
    }

    @Override
    public Flux<MessageResponse> getMessages(Long roomId) {
        return SecurityUtils.getCurrentUser()
                .flatMapMany(user -> {

                    return chatRoomRepository.findById(roomId)
                            .switchIfEmpty(
                                    Mono.error(new ChatRoomNotFoundException(roomId))
                            )
                            .flatMapMany(chatRoom -> {

                                return chatRoomMemberRepository
                                        .findByRoomIdAndUserId(roomId, user.getId())
                                        .switchIfEmpty(
                                                Mono.error(new RoomAccessDeniedException())
                                        )
                                        .flatMapMany(member -> {

                                            return messageRepository
                                                    .findByRoomIdOrderByCreatedAtAsc(roomId)
                                                    .map(messageMapper::toResponse);

                                        });

                            });

                });
    }
}
