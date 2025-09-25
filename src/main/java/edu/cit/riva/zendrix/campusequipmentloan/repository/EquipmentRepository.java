package edu.cit.riva.zendrix.campusequipmentloan.repository;

import edu.cit.riva.zendrix.campusequipmentloan.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<Equipment> findByAvailableTrue();
}
