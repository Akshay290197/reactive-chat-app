package com.chat.reactive.entity;

import com.chat.reactive.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "chat", name = "users")
public class User extends BaseEntity {
    @Id
    private Long id;

    private String username;

    private String email;

    private String password;

    private String displayName;

    private String profilePicture;

    private UserStatus status;

    private LocalDateTime lastSeen;

}
