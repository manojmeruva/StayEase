package com.example.stayeaseapp.Exceptions;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException(String message){
        super(message);
    }
}
