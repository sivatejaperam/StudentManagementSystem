package com.sivateja.springbootjpademo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
public class ErrorDetails {

    private String errorCode;
    private String errorMessage;
    private String devErrorMessage;
    private Map<String, Object> additionalData;

}
