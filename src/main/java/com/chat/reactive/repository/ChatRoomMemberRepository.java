package com.chat.reactive.repository;

import com.chat.reactive.entity.ChatRoomMembers;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ChatRoomMemberRepository extends ReactiveCrudRepository<ChatRoomMembers, Long> {
    Mono<ChatRoomMembers> findByRoomIdAndUserId(Long roomId, Long userId);

    Flux<ChatRoomMembers> findByUserId(Long userId);
}
