package com.mundackal.UserService.exception;

public class RoleNotFoundException extends Exception {
    RoleNotFoundException(){}
    public RoleNotFoundException(String message){
        super(message);
    }
    public RoleNotFoundException(String message,Throwable cause){
        super(message,cause);
    }
}
