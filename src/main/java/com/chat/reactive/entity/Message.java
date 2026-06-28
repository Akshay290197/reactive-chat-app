package com.chat.reactive.entity;

import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("messages")
public class Message extends BaseEntity {
    @Id
    private Long id;

    private Long roomId;

    private Long senderId;

    private String content;

    private MessageType type;

    private MessageStatus status;

    private String fileUrl;

    private Boolean isEdited;

    private Boolean isDeleted;
}
