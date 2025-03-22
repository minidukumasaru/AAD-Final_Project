package org.example.aadfinal_project.repo;

import org.example.aadfinal_project.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, Integer> {
    // Custom query method to find a vehicle by registration number
    // Custom query method to find a vehicle by registration number
    Optional<Vehicle> findByRegistrationNumber(String registrationNumber);

    // Check if registration number exists
    boolean existsByRegistrationNumber(String registrationNumber);
}
