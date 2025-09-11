package edu.cit.riva.zendrix.campusequipmentloan.service.penalty;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DailyPenaltyStrategy implements PenaltyStrategy {

    private static final double PENALTY_PER_DAY = 50.0;

    @Override
    public double calculatePenalty(LocalDate dueDate, LocalDate returnDate) {
        if (returnDate == null || returnDate.isBefore(dueDate)) {
            return 0.0;
        }
        long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);
        return daysLate > 0 ? daysLate * PENALTY_PER_DAY : 0.0;
    }
}
