package edu.cit.riva.zendrix.campusequipmentloan.controller;

import edu.cit.riva.zendrix.campusequipmentloan.model.Equipment;
import edu.cit.riva.zendrix.campusequipmentloan.service.LoanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

    private final LoanService loanService;

    public EquipmentController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/available")
    public List<Equipment> listAvailableEquipment() {
        return loanService.listAvailableEquipment();
    }

    @PostMapping("/add")
    public Equipment addEquipment(@RequestBody Equipment equipment) {
        equipment.setAvailable(true);
        return loanService.saveEquipment(equipment);
    }
}
