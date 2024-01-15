package com.mundackal.UserService.exception;

public class UserNotFoundException extends Exception{
    UserNotFoundException(){
    }
    public UserNotFoundException(String message){
        super(message);
    }
    public UserNotFoundException(String message, Throwable cause){
        super(message,cause);
    }
}
