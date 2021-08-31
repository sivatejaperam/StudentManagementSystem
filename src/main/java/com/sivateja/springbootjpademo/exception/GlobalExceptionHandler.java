package com.sivateja.springbootjpademo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity handleResourceNotFoundException(ResourceNotFoundException ex){
        log.error("Resource not found ", ex);
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setErrorCode(HttpStatus.NOT_FOUND.toString());
        errorDetails.setErrorMessage(ex.getMessage());
        errorDetails.setDevErrorMessage(getStackTraceAsString(ex));
        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getStackTraceAsString(Exception e)
    {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setErrorCode(HttpStatus.BAD_REQUEST.toString());
        HashMap<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error ->{
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });
        errorDetails.setErrorMessage(errors.toString());
        errorDetails.setDevErrorMessage(getStackTraceAsString(ex));
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }


}
