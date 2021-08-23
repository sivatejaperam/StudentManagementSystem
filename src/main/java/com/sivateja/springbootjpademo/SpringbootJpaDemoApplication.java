package com.sivateja.springbootjpademo;

import com.sivateja.springbootjpademo.entity.Student;
import com.sivateja.springbootjpademo.repo.StudentRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringbootJpaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaDemoApplication.class, args);
	}

	/*@Bean
	CommandLineRunner commandLineRunner(StudentRepo repo){
		return args -> {
			List<Student> students = IntStream.rangeClosed(1, 100)
					.mapToObj(i -> new Student("Student" + i, new Random().nextInt(100)))
					.collect(Collectors.toList());
			repo.saveAll(students);
		};
	}*/
}
