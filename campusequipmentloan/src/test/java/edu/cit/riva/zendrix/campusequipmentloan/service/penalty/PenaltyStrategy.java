package edu.cit.riva.zendrix.campusequipmentloan.service.penalty;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public interface PenaltyStrategy {
    double calculatePenalty(LocalDate dueDate, LocalDate returnDate);
}
