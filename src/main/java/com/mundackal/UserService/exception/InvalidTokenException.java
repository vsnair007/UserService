package com.mundackal.UserService.exception;

public class InvalidTokenException extends Exception{
    InvalidTokenException(){}
    public InvalidTokenException(String message){super(message);}
    public InvalidTokenException(String message,Throwable cause ){super(message,cause);}
}
