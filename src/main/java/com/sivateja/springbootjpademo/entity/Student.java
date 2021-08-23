package com.sivateja.springbootjpademo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "Student Name is mandatory")
    @Column(unique = true)
    private String name;
    private int score;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate = new Date();
    @Temporal(TemporalType.TIMESTAMP)
    private Date UpdatedDate = new Date();

    public Student(String name, int score){
        this.name = name;
        this.score = score;
    }
}
