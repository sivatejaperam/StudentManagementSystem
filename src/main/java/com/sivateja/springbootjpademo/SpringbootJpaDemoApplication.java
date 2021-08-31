package com.sivateja.springbootjpademo;

import com.sivateja.springbootjpademo.entity.Branch;
import com.sivateja.springbootjpademo.entity.Student;
import com.sivateja.springbootjpademo.repo.BranchRepo;
import com.sivateja.springbootjpademo.repo.StudentRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Date;
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
	CommandLineRunner commandLineRunner(BranchRepo repo){
		return args -> {
			repo.save(new Branch(null, "E.C.E", LocalDate.now(), LocalDate.of(2021,9,1), null));
		};
	}*/
}
