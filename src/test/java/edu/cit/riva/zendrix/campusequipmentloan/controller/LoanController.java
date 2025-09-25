package edu.cit.riva.zendrix.campusequipmentloan.controller;

import edu.cit.riva.zendrix.campusequipmentloan.model.Loan;
import edu.cit.riva.zendrix.campusequipmentloan.service.LoanService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    // Create a loan
    @PostMapping
    public Loan createLoan(@RequestParam Long equipmentId, @RequestParam Long studentId) {
        return loanService.createLoan(equipmentId, studentId);
    }

    // Return a loan
    @PostMapping("/{id}/return")
    public String returnLoan(@PathVariable Long id) {
        double penalty = loanService.returnLoan(id);
        if (penalty > 0) {
            return "Loan returned with penalty: ₱" + penalty;
        }
        return "Loan returned successfully";
    }
}
