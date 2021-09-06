package com.sivateja.springbootjpademo.controller;

import com.sivateja.springbootjpademo.entity.AppUser;
import com.sivateja.springbootjpademo.exception.ResourceNotFoundException;
import com.sivateja.springbootjpademo.repo.UserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.*;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    private UserRepo userRepo;

    UserController(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Value("${file.upload.location}")
    private String uploadLocation;

    @PostMapping
    public AppUser save(@RequestBody AppUser user) {
        return userRepo.save(user);
    }

    @GetMapping
    public List<AppUser> getUsers(){
        return userRepo.findAll();
    }

    @PostMapping("/profile/image/{id}")
    public ResponseEntity saveProfilePic(@PathVariable Long id, @RequestParam MultipartFile file) {
        log.info(String.format("upload start of photo (%s size)", file.getSize()));
        AppUser appUser = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        File root = new File(uploadLocation, appUser.getName());
        if (!root.exists()) {
            root.mkdirs();
        }
        File uploadedFile = new File(root, file.getOriginalFilename());
        try (InputStream in = file.getInputStream(); OutputStream out = new FileOutputStream(uploadedFile)) {
            FileCopyUtils.copy(in, out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        appUser.setImageLocation(uploadedFile.getAbsolutePath());
        userRepo.save(appUser);
        log.info(String.format("upload-finish (%s)", uploadLocation));
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest()
                .toUriString());
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/profile/image")
    public ResponseEntity getProfilePic(@PathVariable Long id){
        return userRepo.findById(id).map(user ->{
            String filePath = user.getImageLocation();
            Resource image = new FileSystemResource(filePath);
            if(!image.exists()){
                throw new ResourceNotFoundException("File not found in path "+ filePath);
            }
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
        }).orElseThrow(() -> new ResourceNotFoundException("User not found with id "+ id));
    }
}
