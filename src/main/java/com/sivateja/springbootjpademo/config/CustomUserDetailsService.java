package com.sivateja.springbootjpademo.config;

import com.sivateja.springbootjpademo.entity.AppUser;
import com.sivateja.springbootjpademo.exception.ResourceNotFoundException;
import com.sivateja.springbootjpademo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email " + email));
        
        return new UserPrincipal(appUser.getId(), appUser.getEmail(), appUser.getPassword(), new ArrayList<>());
    }

    public UserDetails loadByUserId(Long userId){
        AppUser appUser = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));

        return new UserPrincipal(appUser.getId(), appUser.getEmail(), appUser.getPassword(), new ArrayList<>());
    }

}
