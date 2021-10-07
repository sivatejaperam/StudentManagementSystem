package com.sivateja.springbootjpademo.service;

import com.sivateja.springbootjpademo.entity.AppUser;
import com.sivateja.springbootjpademo.exception.ResourceNotFoundException;
import com.sivateja.springbootjpademo.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.*;
import java.net.URI;
import java.util.List;

@Service
public class UserService {

    @Value("${file.upload.location}")
    private String uploadLocation;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AppUser save(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public List<AppUser> findAll() {
        return userRepo.findAll();
    }

    public AppUser findById(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found with id " + id));
    }

    public Resource getProfilePic(Long id) {
        return userRepo.findById(id).map(user -> {
            String filePath = user.getImageLocation();
            Resource image = new FileSystemResource(filePath);
            if (!image.exists()) {
                throw new ResourceNotFoundException("File not found in path " + filePath);
            }
            return image;
        }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    public void saveProfilePic(Long id, MultipartFile file) {
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

    }
}
