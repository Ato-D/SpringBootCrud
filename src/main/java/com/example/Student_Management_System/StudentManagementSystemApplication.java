package com.example.Student_Management_System;

import com.example.Student_Management_System.entity.Student;
import com.example.Student_Management_System.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.Month;

@SpringBootApplication
public class StudentManagementSystemApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementSystemApplication.class, args);
	}

	@Autowired
	StudentRepository studentRepository;

	@Override
	public void run(String... args) throws Exception {
		Student student1 = new Student(
				1L,
				"Douglas",
				"Dogie",
				"donkoh@gmai.com",
				LocalDate.of(2001, Month.APRIL, 15)

		);
		studentRepository.save(student1);


		Student student2 = new Student(
				2L,
				"Ato",
				"Donkey",
				"donkoh@gmai.com",
				LocalDate.of(1889, Month.MARCH, 30)

		);
		studentRepository.save(student2);

		Student student3 = new Student(
				3L,
				"Paddy",
				"Kwakiutl",
				"paddy@gmai.com",
				LocalDate.of(2008, Month.JUNE, 01)

		);
		studentRepository.save(student3);
	}
	}


