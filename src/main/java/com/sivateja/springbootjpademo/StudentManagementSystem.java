package com.sivateja.springbootjpademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentManagementSystem {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementSystem.class, args);
	}

	/*@Bean
	CommandLineRunner commandLineRunner(BranchRepo repo){
		return args -> {
			repo.save(new Branch(null, "E.C.E", LocalDate.now(), LocalDate.of(2021,9,1), null));
		};
	}*/
}
