package org.example.aadfinal_project.service;

import org.example.aadfinal_project.dto.VehicleDTO;
import java.util.List;

public interface VehicleService {
    int saveVehicle(VehicleDTO vehicleDTO) throws RuntimeException;
    boolean updateVehicle(VehicleDTO vehicleDTO) throws RuntimeException;
    boolean deleteVehicleByRegistrationNumber(String registrationNumber) throws RuntimeException;
    List<VehicleDTO> getAllVehicles();
    VehicleDTO getVehicleByRegistrationNumber(String registrationNumber);
}
