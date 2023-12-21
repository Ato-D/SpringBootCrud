package com.example.Student_Management_System.dto;

import lombok.Data;

import java.time.LocalDate;

@Data

public class StudentDto {

    private long id;
    private String email;
    private LocalDate dob;
}
