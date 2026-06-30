package com.chat.reactive.repository;

import com.chat.reactive.entity.Message;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MessageRepository extends ReactiveCrudRepository<Message, Long> {
    Flux<Message> findByRoomIdOrderByCreatedAtAsc(Long roomId);
}
