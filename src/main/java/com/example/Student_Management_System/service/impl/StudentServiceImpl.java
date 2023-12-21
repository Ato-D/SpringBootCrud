package com.example.Student_Management_System.service.impl;

import com.example.Student_Management_System.dto.StudentDto;
import com.example.Student_Management_System.entity.Student;
import com.example.Student_Management_System.repository.StudentRepository;
import com.example.Student_Management_System.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<StudentDto> getAllStudentsDto() {
       return studentRepository.findAll()
                .stream()
                .map(this::convertStudentToDto)
                .collect(Collectors.toList());
    }

    public StudentDto convertStudentToDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setEmail(student.getEmail());
        studentDto.setDob(student.getDob());

        return studentDto;
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).get();
    }
//        Optional<Student> student = Optional.ofNullable(studentRepository.findById(id)
//                .orElseThrow(() -> new IllegalStateException(
//                        "student with id " + id + " does not exist"
//                )));

//        return studentRepository.save(student);


    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }


    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

}


