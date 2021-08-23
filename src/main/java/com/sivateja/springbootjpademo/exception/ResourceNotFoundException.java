package com.sivateja.springbootjpademo.exception;

public class ResourceNotFoundException extends RuntimeException{


    public ResourceNotFoundException(){
        this("Resource not Found!");
    }

    public ResourceNotFoundException(String message){
        this(message, null);
    }

    public ResourceNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
