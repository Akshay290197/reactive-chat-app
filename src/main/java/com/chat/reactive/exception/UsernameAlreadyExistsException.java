package com.chat.reactive.exception;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException(String username){
        super("user name already exists : " + username);
    }
}
