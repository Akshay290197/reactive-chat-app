package com.chat.reactive.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ProblemDetail handleUserNotFound(UserNotFoundException ex) {

        ProblemDetail problemDetail =
                ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        problemDetail.setTitle("User Not Found");
        problemDetail.setDetail(ex.getMessage());

        return problemDetail;
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ProblemDetail handleInvalidCredentials(InvalidCredentialsException ex) {

        ProblemDetail problemDetail =
                ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);

        problemDetail.setTitle("Authentication Failed");
        problemDetail.setDetail(ex.getMessage());

        return problemDetail;
    }

    @ExceptionHandler(ChatRoomNotFoundException.class)
    public ProblemDetail handleChatRoomNotFound(ChatRoomNotFoundException ex) {

        ProblemDetail problemDetail =
                ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        problemDetail.setTitle("Chat Room Not Found");
        problemDetail.setDetail(ex.getMessage());

        return problemDetail;
    }

    @ExceptionHandler(UserAlreadyJoinedException.class)
    public ProblemDetail handleUserAlreadyJoined(UserAlreadyJoinedException ex) {

        ProblemDetail problemDetail =
                ProblemDetail.forStatus(HttpStatus.CONFLICT);

        problemDetail.setTitle("User Already Joined");
        problemDetail.setDetail(ex.getMessage());

        return problemDetail;
    }

    @ExceptionHandler(RoomAccessDeniedException.class)
    public ProblemDetail RoomAccessDenied(RoomAccessDeniedException ex) {

        ProblemDetail problemDetail =
                ProblemDetail.forStatus(HttpStatus.FORBIDDEN);

        problemDetail.setTitle("User is not a member of room");
        problemDetail.setDetail(ex.getMessage());

        return problemDetail;
    }
}
