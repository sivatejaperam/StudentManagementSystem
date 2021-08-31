package com.sivateja.springbootjpademo.service;

import com.sivateja.springbootjpademo.entity.Branch;
import com.sivateja.springbootjpademo.entity.Student;
import com.sivateja.springbootjpademo.exception.ResourceNotFoundException;
import com.sivateja.springbootjpademo.repo.BranchRepo;
import com.sivateja.springbootjpademo.repo.StudentRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BranchService {

    private BranchRepo branchRepo;
    private StudentRepo studentRepo;

    public Branch saveBranch(Branch branch) {
        return branchRepo.save(branch);
    }

    public Branch addStudentToBranch(Long studentId, Long branchId) {
        Branch branch = branchRepo.findById(branchId)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id = " + branchId));
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id = " + branchId));
        branch.addStudents(student);
        branchRepo.save(branch);
        return branch;
    }

    public List<Branch> findAll() {
        return branchRepo.findAll();
    }
}
