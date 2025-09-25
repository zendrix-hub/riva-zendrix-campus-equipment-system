package edu.cit.riva.zendrix.campusequipmentloan.repository;

import edu.cit.riva.zendrix.campusequipmentloan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
