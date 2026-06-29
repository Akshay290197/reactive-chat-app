package com.chat.reactive.exception;

public class UserAlreadyJoinedException extends RuntimeException{
    public UserAlreadyJoinedException(Long roomId) {
        super("You are already a member of room : " + roomId);
    }
}
