package com.sivateja.springbootjpademo.service;

import com.sivateja.springbootjpademo.entity.Student;
import com.sivateja.springbootjpademo.exception.ResourceNotFoundException;
import com.sivateja.springbootjpademo.repo.StudentRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private StudentRepo repo;

    public Student saveorUpdate(Student student) {
        return repo.save(student);
    }

    public Student findById(Long id){
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student Not Found with id "+ id));
    }

    public List<Student> findAll() {
        return repo.findAll();
    }

    public Page<Student> getStudentsWithPagingAndSorting(int pageSize, int offSet, String sortField) {
        Page<Student> students = repo.findAll(PageRequest.of(offSet, pageSize).withSort(Sort.Direction.ASC, sortField));
        return students;
    }

    public Page<Student> getStudentsWithPaging(int pageSize, int offSet) {
        Page<Student> students = repo.findAll(PageRequest.of(offSet, pageSize));
        return students;
    }

    public List<Student> getStudentsWithSorting(String field) {
        return repo.findAll(Sort.by(Sort.Direction.ASC, field));
    }


    public void deleteStudent(Long id) {
        repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student Not Found with id "+ id));
        repo.deleteById(id);
    }
}
