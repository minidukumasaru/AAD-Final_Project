package org.example.aadfinal_project.service.impl;

import org.example.aadfinal_project.dto.VehicleDTO;
import org.example.aadfinal_project.entity.Vehicle;
import org.example.aadfinal_project.repo.VehicleRepo;
import org.example.aadfinal_project.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepo vehicleRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public VehicleServiceImpl(VehicleRepo vehicleRepo, ModelMapper modelMapper) {
        this.vehicleRepo = vehicleRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public int saveVehicle(VehicleDTO vehicleDTO) {
        // Check if a vehicle with the same registration number already exists
        if (vehicleRepo.existsByRegistrationNumber(vehicleDTO.getRegistrationNumber())) {
            throw new RuntimeException("Vehicle with registration number " + vehicleDTO.getRegistrationNumber() + " already exists");
        }

        // Create a new Vehicle entity by mapping the DTO to the entity
        Vehicle vehicle = modelMapper.map(vehicleDTO, Vehicle.class);

        // Save the vehicle entity in the database
        Vehicle savedVehicle = vehicleRepo.save(vehicle);

        // Return the ID of the saved vehicle
        return savedVehicle.getId();
    }

    @Override
    @Transactional
    public boolean updateVehicle(VehicleDTO vehicleDTO) {
        // Fetch the existing vehicle by registration number
        Optional<Vehicle> optionalVehicle = vehicleRepo.findByRegistrationNumber(vehicleDTO.getRegistrationNumber());

        if (optionalVehicle.isEmpty()) {
            throw new RuntimeException("Vehicle not found with Registration Number: " + vehicleDTO.getRegistrationNumber());
        }

        Vehicle existingVehicle = optionalVehicle.get();

        // Update fields manually to avoid overwriting existing data
        existingVehicle.setType(vehicleDTO.getType());
        existingVehicle.setCapacity(vehicleDTO.getCapacity());
        existingVehicle.setAvailable(vehicleDTO.getAvailable());
        existingVehicle.setPricePerDay(vehicleDTO.getPricePerDay());

        // Save the updated entity
        vehicleRepo.save(existingVehicle);

        return true; // Indicate successful update
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        // Fetch all vehicles from the database
        List<Vehicle> vehicles = vehicleRepo.findAll();

        // Convert the list of vehicles to a list of VehicleDTOs
        return vehicles.stream()
                .map(vehicle -> modelMapper.map(vehicle, VehicleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean deleteVehicleByRegistrationNumber(String registrationNumber) {
        Optional<Vehicle> optionalVehicle = vehicleRepo.findByRegistrationNumber(registrationNumber);

        if (optionalVehicle.isEmpty()) {
            return false; // Vehicle not found
        }

        // Extract the vehicle from Optional and delete it
        Vehicle vehicle = optionalVehicle.get();
        vehicleRepo.delete(vehicle);

        return true;
    }

    @Override
    public VehicleDTO getVehicleByRegistrationNumber(String registrationNumber) {
        Optional<Vehicle> optionalVehicle = vehicleRepo.findByRegistrationNumber(registrationNumber);

        if (optionalVehicle.isEmpty()) {
            return null; // Vehicle not found
        }

        // Convert entity to DTO
        return modelMapper.map(optionalVehicle.get(), VehicleDTO.class);
    }
}