package com.chat.reactive.entity;

import com.chat.reactive.enums.ChatRoomType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("chat_rooms")
public class ChatRoom extends BaseEntity {
    @Id
    private Long id;

    private String name;

    private String description;

    private ChatRoomType type;

    private Long createdBy;

    private String imageUrl;

    private Boolean isActive;
}
