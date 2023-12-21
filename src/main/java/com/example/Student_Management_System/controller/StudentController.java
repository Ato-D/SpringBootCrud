package com.example.Student_Management_System.controller;

import com.example.Student_Management_System.dto.StudentDto;
import com.example.Student_Management_System.entity.Student;
import com.example.Student_Management_System.service.StudentService;
import io.swagger.annotations.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Handles the GET request to list all students.
     *
     * @param model the Spring Model to which attributes can be added for view rendering
     * @return the view name to render (e.g., "students") or an error view name
     */



    @GetMapping("/students")
    public String listStudents(Model model) {
        try {
            // Info log statement indicating the intention to list all students
            log.info("Listing all students...");

            // Add students to the model
            model.addAttribute("students", studentService.getAllStudents());

            // Return the view name for successfully listing students
            return "students";
        } catch (Exception e) {
            // Error log statement if an exception occurs while listing students
            log.error("An error occurred while listing students", e);

            // Return the error view name; replace with the appropriate error view name if available
            return "error";
        }
    }

//    @GetMapping("/students/students-dto")
//    public List<StudentDto> getAllStudentsDto(){
//        return studentService.getAllStudentsDto();
//    }

    @GetMapping("/students/students-dto")
    public String listStudentsDto(Model model) {
        model.addAttribute("students", studentService.getAllStudentsDto());
        return "student_dto";
    }

    /**
     * Handles the GET request to display the form for creating a new student.
     *
     * @param model the Spring Model to which attributes can be added for view rendering
     * @return the view name for the student creation form (e.g., "create_student")
     */
    @GetMapping("/students/new")
    public String createStudentForm(Model model) {
        // Create a new student object to hold student form data
        Student student = new Student();

        // Add the newly created student object to the model for rendering the create form
        model.addAttribute("student", student);

        // Return the view name for the student creation form
        return "create_student";
    }

    /**
     * Handles the POST request to save a new student or update an existing one.
     *
     * @param student the student object containing the data from the form
     * @return the redirect URL after saving the student (usually back to the student list)
     */
    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student) {
        // Save the student data to the database using the studentService
        studentService.saveStudent(student);

        // Info log statement indicating the successful save of a student
        log.info("Student saved successfully. Student ID: {}", student.getId());

        // Redirect to the root ("/") after saving the student
        return "redirect:/students";
    }

    /**
     * Handles the GET request to display the form for editing a student.
     *
     * @param id    the ID of the student to be edited
     * @param model the Spring Model to which attributes can be added for view rendering
     * @return the view name for the student edit form (e.g., "edit_student")
     */
    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        // Add the student with the specified ID to the model for rendering the edit form
        model.addAttribute("student", studentService.getStudentById(id));

        // Return the view name for the student edit form
        return "edit_student";
    }

    /**
     * Handles the POST request to update a student.
     *
     * @param id      the ID of the student to be updated
     * @param student the updated student data provided through the form
     * @param model   the Spring Model to which attributes can be added for view rendering
     * @return the view or redirect URL after updating the student
     */
    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id,
                                @ModelAttribute("student") Student student,
                                Model model) {
        try {
            // Retrieve the existing student from the database by ID
            Student existingStudent = studentService.getStudentById(id);

            // Update the existing student with the new data
            existingStudent.setId(id);
            existingStudent.setFirstName(student.getFirstName());
            existingStudent.setLastName(student.getLastName());
            existingStudent.setEmail(student.getEmail());
            existingStudent.setDob(student.getDob());

            // Save the updated student object
            studentService.updateStudent(existingStudent);

            // Info log statement indicating the successful update of a student
            log.info("Student updated successfully. Student ID: {}", id);

            // Redirect to the root ("/") after updating the student
            return "redirect:/students";
        } catch (Exception e) {
            // Error log statement if an exception occurs while updating the student
            log.error("An error occurred while updating student with ID {}", id, e);

            // Return the error view name; replace with the appropriate error view name if available
            return "error";
        }
    }



    /**
     * Handles the GET request to delete a student by ID.
     *
     * @param id the ID of the student to be deleted
     * @return the redirect URL after deleting the student
     */
    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id) {
        try {
            // Delete the student from the database by ID
            studentService.deleteStudentById(id);

            // Info log statement indicating the successful deletion of a student
            log.info("Student deleted successfully. Student ID: {}", id);

            // Redirect to the root ("/") after deleting the student
            return "redirect:/students";
        } catch (Exception e) {
            // Error log statement if an exception occurs while deleting the student
            log.error("An error occurred while deleting student with ID {}", id, e);

            // Return the error view name; replace with the appropriate error view name if available
            return "error";
        }
    }
}
