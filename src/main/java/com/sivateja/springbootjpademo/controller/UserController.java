package com.sivateja.springbootjpademo.controller;

import com.sivateja.springbootjpademo.entity.AppUser;
import com.sivateja.springbootjpademo.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserRepo userRepo;

    @PostMapping
    public AppUser save(@RequestBody AppUser user){
        return userRepo.save(user);
    }

    public ResponseEntity profile(){
        return null;
    }
}
