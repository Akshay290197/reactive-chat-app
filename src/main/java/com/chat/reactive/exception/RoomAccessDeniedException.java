package com.chat.reactive.exception;

public class RoomAccessDeniedException extends RuntimeException{
    public RoomAccessDeniedException() {
        super("You are not a member of this chat room.");
    }
}
