package edu.cit.riva.zendrix.campusequipmentloan.repository;

import edu.cit.riva.zendrix.campusequipmentloan.model.Loan;
import edu.cit.riva.zendrix.campusequipmentloan.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByStudentAndStatus(Student student, String status);
}
