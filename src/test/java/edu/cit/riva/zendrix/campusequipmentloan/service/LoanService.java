package edu.cit.riva.zendrix.campusequipmentloan.service;

import edu.cit.riva.zendrix.campusequipmentloan.model.*;
import edu.cit.riva.zendrix.campusequipmentloan.repository.*;
import edu.cit.riva.zendrix.campusequipmentloan.service.penalty.PenaltyStrategy;
import edu.cit.riva.zendrix.campusequipmentloan.service.penalty.DailyPenaltyStrategy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final EquipmentRepository equipmentRepository;
    private final StudentRepository studentRepository;
    private final PenaltyStrategy penaltyStrategy = new DailyPenaltyStrategy();

    public LoanService(LoanRepository loanRepository,
                       EquipmentRepository equipmentRepository,
                       StudentRepository studentRepository) {
        this.loanRepository = loanRepository;
        this.equipmentRepository = equipmentRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public Loan createLoan(Long equipmentId, Long studentId) {
        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new RuntimeException("Equipment not found"));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (!equipment.isAvailable()) {
            throw new RuntimeException("Equipment not available");
        }

        List<Loan> activeLoans = loanRepository.findByStudentAndStatus(student, "ACTIVE");
        if (activeLoans.size() >= 2) {
            throw new RuntimeException("Student already has 2 active loans");
        }

        Loan loan = new Loan();
        loan.setEquipment(equipment);
        loan.setStudent(student);
        loan.setStartDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusDays(7));
        loan.setStatus("ACTIVE");

        equipment.setAvailable(false);
        equipmentRepository.save(equipment);

        return loanRepository.save(loan);
    }

    @Transactional
    public double returnLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        if (!"ACTIVE".equals(loan.getStatus())) {
            throw new RuntimeException("Loan is not active");
        }

        loan.setReturnDate(LocalDate.now());

        double penalty = penaltyStrategy.calculatePenalty(loan.getDueDate(), loan.getReturnDate());
        loan.setStatus(penalty > 0 ? "OVERDUE" : "RETURNED");

        Equipment equipment = loan.getEquipment();
        equipment.setAvailable(true);
        equipmentRepository.save(equipment);

        loanRepository.save(loan);
        return penalty;
    }

    public List<Equipment> listAvailableEquipment() {
        return equipmentRepository.findByAvailableTrue();
    }
}
