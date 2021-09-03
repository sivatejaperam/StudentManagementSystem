package com.sivateja.springbootjpademo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "Student Name is mandatory")
    @Column(unique = true)
    private String name;
    @JsonManagedReference
    @ManyToOne
    private Branch branch;
    @OneToMany(mappedBy = "student")
    private List<Subject> subjects= new ArrayList<>();

    public void addSubjects(Subject subject){
        this.subjects.add(subject);
        subject.setStudent(this);
    }

}
