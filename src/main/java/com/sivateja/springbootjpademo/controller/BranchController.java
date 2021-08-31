package com.sivateja.springbootjpademo.controller;

import com.sivateja.springbootjpademo.entity.Branch;
import com.sivateja.springbootjpademo.service.BranchService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/branch")
@AllArgsConstructor
public class BranchController {

    private BranchService branchService;

    @PostMapping
    public Branch save(@RequestBody @Valid Branch branch){
        return branchService.saveBranch(branch);
    }

    @GetMapping
    public List<Branch> getAll(){
        return branchService.findAll();
    }

    @PostMapping("/addStudent/{studentId}/{branchId}")
    public Branch addStudent(@PathVariable Long studentId, @PathVariable Long branchId){
        return branchService.addStudentToBranch(studentId,branchId);
    }
}
