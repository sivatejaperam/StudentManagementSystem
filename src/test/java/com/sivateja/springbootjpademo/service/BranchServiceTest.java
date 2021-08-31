package com.sivateja.springbootjpademo.service;


import com.sivateja.springbootjpademo.entity.Branch;
import com.sivateja.springbootjpademo.entity.Student;
import com.sivateja.springbootjpademo.repo.BranchRepo;
import com.sivateja.springbootjpademo.repo.StudentRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class BranchServiceTest {

    @MockBean
    private BranchRepo branchRepo;

    @MockBean
    private StudentRepo studentRepo;

    private Branch branch;
    private Student student;
    private BranchService branchService;

    public BranchServiceTest() {
    }

    @BeforeEach
    public void setUp() {
        branch = new Branch(null, "E.C.E", LocalDate.now(), LocalDate.of(2021, 9, 1), new ArrayList<>());
        student = new Student(1L, "student1", null, null);
        branchService = new BranchService(branchRepo, studentRepo);
    }


    @Test
    @DisplayName("this test should pass when the given branch is saved")
    public void shouldSaveTheBranch() {
        Mockito.when(branchRepo.save(branch)).thenReturn(branch);
        Assertions.assertEquals(branch, branchService.saveBranch(branch));
    }

    @Test
    public void shouldAddStudentToBranch() {
        Mockito.when(branchRepo.findById(1L)).thenReturn(Optional.of(branch));
        Mockito.when(studentRepo.findById(1L)).thenReturn(java.util.Optional.of(student));
        Mockito.when(branchRepo.save(branch)).thenReturn(branch);
        Assertions.assertEquals(branch, branchService.addStudentToBranch(1L, 1L));
    }

    @Test
    public void shouldReturnAllBranches(){
        Mockito.when(branchRepo.findAll()).thenReturn(Collections.singletonList(branch));
        Assertions.assertEquals(branchService.findAll(), Collections.singletonList(branch));
    }
}
