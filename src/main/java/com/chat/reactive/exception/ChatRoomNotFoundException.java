package com.chat.reactive.exception;

public class ChatRoomNotFoundException extends RuntimeException{
    public ChatRoomNotFoundException(Long id) {
        super("Chat room not found with id : " + id);
    }
}
