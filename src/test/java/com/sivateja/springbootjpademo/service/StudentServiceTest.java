package com.sivateja.springbootjpademo.service;

import com.sivateja.springbootjpademo.entity.Student;
import com.sivateja.springbootjpademo.repo.StudentRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class StudentServiceTest {

    @MockBean
    private StudentRepo studentRepo;

    private StudentService studentService;
    private Student student;

    @BeforeEach
    public void setUp(){
        studentService = new StudentService(studentRepo);
        student = new Student(1L, "student1", null, null);
    }

    @Test
    void shouldSaveOrUpdateStudent() {
        Mockito.when(studentRepo.save(ArgumentMatchers.any(Student.class))).thenReturn(student);
        Assertions.assertEquals(studentService.saveorUpdate(student),student);
    }

    @Test
    void shouldFindTheStudentById() {
        Mockito.when(studentRepo.findById(ArgumentMatchers.any(Long.class))).thenReturn(Optional.of(student));
        Assertions.assertEquals(studentService.findById(1L),student);
    }

    @Test
    void shouldFindAll() {
        Mockito.when(studentRepo.findAll()).thenReturn(Collections.singletonList(student));
        Assertions.assertEquals(studentService.findAll(), Collections.singletonList(student));
    }

    @Test
    void shouldGetStudentsWithPagingAndSorting() {
        Page<Student> page = new PageImpl<>(Collections.singletonList(student));
        Mockito.when(studentRepo.findAll(ArgumentMatchers.any(PageRequest.class))).thenReturn(page);
        Assertions.assertEquals(studentService.getStudentsWithPagingAndSorting(10,0,"name"), page);
    }

    @Test
    void shouldGetStudentsWithPaging() {
        Page<Student> page = new PageImpl<>(Collections.singletonList(student));
        Mockito.when(studentRepo.findAll(ArgumentMatchers.any(PageRequest.class))).thenReturn(page);
        Assertions.assertEquals(studentService.getStudentsWithPaging(10,0), page);
    }

    @Test
    void shouldGetStudentsWithSorting() {
        Mockito.when(studentRepo.findAll(ArgumentMatchers.any(Sort.class))).thenReturn(Collections.singletonList(student));
        Assertions.assertEquals(studentService.getStudentsWithSorting("name"), Collections.singletonList(student));
    }

    @Test
    void shouldDeleteStudent() {
        Mockito.when(studentRepo.findById(ArgumentMatchers.any(Long.class))).thenReturn(Optional.of(student));
        studentService.deleteStudent(1L);
        Mockito.verify(studentRepo, Mockito.times(1)).deleteById(ArgumentMatchers.any(Long.class));

    }
}