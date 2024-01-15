package com.mundackal.UserService.exception;

public class PassswordNotMatchingException extends Exception{
    PassswordNotMatchingException(){}
    public PassswordNotMatchingException(String message){super(message);}
    public PassswordNotMatchingException(String message,Throwable cause){super(message,cause);}
}
