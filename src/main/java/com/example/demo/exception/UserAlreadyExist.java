package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserAlreadyExist extends RuntimeException {
    public UserAlreadyExist() {
        super();
    }

    public UserAlreadyExist(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAlreadyExist(String message) {
        super(message);
    }

    public UserAlreadyExist(Throwable cause) {
        super(cause);
    }
}
