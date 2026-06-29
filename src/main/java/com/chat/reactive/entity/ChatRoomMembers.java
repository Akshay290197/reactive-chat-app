package com.chat.reactive.entity;

import com.chat.reactive.enums.ChatRoomMemberRole;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(schema = "chat", name = "chat_room_members")
public class ChatRoomMembers extends BaseEntity {
    @Id
    private Long id;

    private Long roomId;

    private Long userId;

    private ChatRoomMemberRole role;

    private LocalDateTime joinedAt;

    private Boolean isActive;
}
