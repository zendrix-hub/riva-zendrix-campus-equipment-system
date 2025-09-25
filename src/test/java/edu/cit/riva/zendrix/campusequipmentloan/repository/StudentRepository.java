package edu.cit.riva.zendrix.campusequipmentloan.repository;

import edu.cit.riva.zendrix.campusequipmentloan.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByStudentNo(String studentNo);
}
