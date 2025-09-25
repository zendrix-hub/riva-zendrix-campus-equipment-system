package edu.cit.riva.zendrix.campusequipmentloan.repository;

import edu.cit.riva.zendrix.campusequipmentloan.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
