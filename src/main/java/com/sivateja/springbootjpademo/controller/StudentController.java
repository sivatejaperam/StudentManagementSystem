package com.sivateja.springbootjpademo.controller;

import com.sivateja.springbootjpademo.entity.Student;
import com.sivateja.springbootjpademo.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/student")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class StudentController {

    private StudentService service;

    @PostMapping
    public Student save(@RequestBody  @Valid Student student){
        return service.saveorUpdate(student);
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return service.findById(id);
    }


    @GetMapping("/sort/{field}")
    public List<Student> getStudentsWithSorting(@PathVariable String field) {
        return service.getStudentsWithSorting(field);
    }

    @GetMapping("/{pageSize}/{offSet}")
    public Page<Student> getStudentsWithPaging(@PathVariable Integer pageSize, @PathVariable Integer offSet) {
        return service.getStudentsWithPaging(pageSize, offSet);
    }


    @GetMapping("/{pageSize}/{offSet}/{sortField}")
    public Page<Student> getStudentsWithPagindAndSorting(@PathVariable Integer pageSize, @PathVariable Integer offSet, @PathVariable String sortField) {
        return service.getStudentsWithPagingAndSorting(pageSize, offSet, sortField);
    }

    @DeleteMapping("{id}")
    public void deleteStudent(@PathVariable Long id){
        service.deleteStudent(id);
    }
}
