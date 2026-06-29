package com.chat.reactive.repository;

import com.chat.reactive.entity.ChatRoom;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ChatRoomRepository extends ReactiveCrudRepository<ChatRoom, Long> {
}
