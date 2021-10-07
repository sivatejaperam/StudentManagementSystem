package com.sivateja.springbootjpademo.controller;

import com.sivateja.springbootjpademo.entity.AppUser;
import com.sivateja.springbootjpademo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<AppUser> getUsers() {
        return userService.findAll();
    }

    @PostMapping("/profile/image/{id}")
    public ResponseEntity saveProfilePic(@PathVariable Long id, @RequestParam MultipartFile file) {
        userService.saveProfilePic(id, file);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest()
                .toUriString());
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/profile/image")
    public ResponseEntity getProfilePic(@PathVariable Long id) {
        Resource image = userService.getProfilePic(id);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }
}
