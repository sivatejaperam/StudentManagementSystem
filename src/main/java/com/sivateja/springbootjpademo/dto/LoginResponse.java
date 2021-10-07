package com.sivateja.springbootjpademo.dto;

import lombok.Data;

@Data
public class LoginResponse {

    private String access_token;
    private String refresh_token;
}
