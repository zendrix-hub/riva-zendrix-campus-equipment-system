package edu.cit.riva.zendrix.campusequipmentloan.controller;

import edu.cit.riva.zendrix.campusequipmentloan.model.Student;
import edu.cit.riva.zendrix.campusequipmentloan.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;
import edu.cit.riva.zendrix.campusequipmentloan.service.LoanService;
import java.util.List;
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }
}
