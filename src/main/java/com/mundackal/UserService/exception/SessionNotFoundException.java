package com.mundackal.UserService.exception;

public class SessionNotFoundException extends Exception{
    SessionNotFoundException(){}
    public SessionNotFoundException(String message){super(message);}
    public SessionNotFoundException(String message,Throwable cause){super(message,cause);}
}
