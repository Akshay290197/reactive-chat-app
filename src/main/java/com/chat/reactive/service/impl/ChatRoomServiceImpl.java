package com.chat.reactive.service.impl;

import com.chat.reactive.config.SecurityUtils;
import com.chat.reactive.dto.request.CreateChatRoomRequest;
import com.chat.reactive.dto.response.ChatRoomResponse;
import com.chat.reactive.entity.ChatRoom;
import com.chat.reactive.entity.ChatRoomMembers;
import com.chat.reactive.enums.ChatRoomMemberRole;
import com.chat.reactive.exception.ChatRoomNotFoundException;
import com.chat.reactive.exception.UserAlreadyJoinedException;
import com.chat.reactive.mapper.ChatRoomMapper;
import com.chat.reactive.repository.ChatRoomMemberRepository;
import com.chat.reactive.repository.ChatRoomRepository;
import com.chat.reactive.service.interfaces.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomMapper chatRoomMapper;
    private final ChatRoomMemberRepository chatRoomMemberRepository;

    @Override
    public Mono<ChatRoomResponse> createChatRoom(CreateChatRoomRequest request) {
        ChatRoom chatRoom = chatRoomMapper.toEntity(request);

        chatRoom.setCreatedAt(LocalDateTime.now());
        chatRoom.setUpdatedAt(LocalDateTime.now());

        chatRoom.setIsActive(true);

        return SecurityUtils.getCurrentUser()
                .flatMap(user -> {
                    ChatRoomMembers member = new ChatRoomMembers();

                    member.setUserId(user.getId());
                    member.setRole(ChatRoomMemberRole.ADMIN);
                    member.setJoinedAt(LocalDateTime.now());
                    member.setIsActive(true);
                    member.setCreatedAt(LocalDateTime.now());
                    member.setUpdatedAt(LocalDateTime.now());

                    chatRoom.setCreatedBy(user.getId());

                    return chatRoomRepository.save(chatRoom)
                            .flatMap(savedRoom -> {

                                member.setRoomId(savedRoom.getId());

                                return chatRoomMemberRepository.save(member)
                                        .thenReturn(savedRoom);
                            });
                })
                .map(chatRoomMapper::toResponse);
    }

    @Override
    public Flux<ChatRoomResponse> getAllChatRooms() {
        return chatRoomRepository.findAll()
                .map(chatRoomMapper::toResponse);
    }

    @Override
    public Mono<ChatRoomResponse> getChatRoomById(Long id) {
        return chatRoomRepository.findById(id)
                .switchIfEmpty(
                        Mono.error(new ChatRoomNotFoundException(id))
                )
                .map(chatRoomMapper::toResponse);
    }

    @Override
    public Mono<Void> joinRoom(Long roomId) {
        return SecurityUtils.getCurrentUser()
                .flatMap(user -> {

                    return chatRoomRepository.findById(roomId)
                            .switchIfEmpty(
                                    Mono.error(new ChatRoomNotFoundException(roomId))
                            )
                            .flatMap(chatRoom -> {

                                return chatRoomMemberRepository
                                        .findByRoomIdAndUserId(roomId, user.getId())
                                        .doOnNext(member -> System.out.println("ALREADY FOUND"))
                                        .flatMap(member ->
                                                Mono.<Void>error(new UserAlreadyJoinedException(roomId))
                                        )
                                        .switchIfEmpty(
                                                Mono.defer(() -> {
                                                    System.out.println("NOT FOUND -> SAVING");

                                                    ChatRoomMembers member = new ChatRoomMembers();

                                                    member.setRoomId(roomId);
                                                    member.setUserId(user.getId());
                                                    member.setRole(ChatRoomMemberRole.MEMBER);
                                                    member.setJoinedAt(LocalDateTime.now());
                                                    member.setIsActive(true);
                                                    member.setCreatedAt(LocalDateTime.now());
                                                    member.setUpdatedAt(LocalDateTime.now());

                                                    return chatRoomMemberRepository.save(member).then();
                                                })
                                        );

                            });

                });
    }
}
