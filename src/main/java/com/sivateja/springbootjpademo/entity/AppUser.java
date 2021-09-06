package com.sivateja.springbootjpademo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name="USER_SEQ", sequenceName = "user_sequence", allocationSize = 1)
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    private Long id;
    private String name;
    private String email;
    private String imageLocation;
    private String password;
    private LocalDateTime createdDate = LocalDateTime.now();


}
