package com.sivateja.springbootjpademo.repo;

import com.sivateja.springbootjpademo.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<AppUser, Long> {
}
