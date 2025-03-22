package org.example.aadfinal_project.controller;

import org.example.aadfinal_project.dto.VehicleDTO;
import org.example.aadfinal_project.service.VehicleService;
import org.example.aadfinal_project.utill.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/vehicle")
public class VehicleController {
    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseUtil> saveVehicle(@RequestBody VehicleDTO vehicleDTO) {
        try {
            int vehicleId = vehicleService.saveVehicle(vehicleDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseUtil(201, "Vehicle Saved Successfully", vehicleId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseUtil(400, e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, "Failed to save vehicle: " + e.getMessage(), null));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseUtil> updateVehicle(@RequestBody VehicleDTO vehicleDTO) {
        try {
            boolean isUpdated = vehicleService.updateVehicle(vehicleDTO);
            return ResponseEntity.ok(new ResponseUtil(200, "Vehicle Updated Successfully", null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseUtil(404, e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, "Failed to update vehicle: " + e.getMessage(), null));
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<VehicleDTO>> getAllVehicles() {
        try {
            List<VehicleDTO> vehicles = vehicleService.getAllVehicles();
            if (vehicles.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(vehicles);
            }
            return ResponseEntity.ok(vehicles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/get/{registrationNumber}")
    public ResponseEntity<?> getVehicleByRegistrationNumber(@PathVariable String registrationNumber) {
        try {
            VehicleDTO vehicleDTO = vehicleService.getVehicleByRegistrationNumber(registrationNumber);
            if (vehicleDTO == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseUtil(404, "Vehicle not found", null));
            }
            return ResponseEntity.ok(vehicleDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, "Error retrieving vehicle: " + e.getMessage(), null));
        }
    }

    @DeleteMapping("/delete/{registrationNumber}")
    public ResponseEntity<ResponseUtil> deleteVehicle(@PathVariable String registrationNumber) {
        try {
            boolean isDeleted = vehicleService.deleteVehicleByRegistrationNumber(registrationNumber);
            if (isDeleted) {
                return ResponseEntity.ok(new ResponseUtil(200, "Vehicle deleted successfully", null));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseUtil(404, "Vehicle not found with registration number: " + registrationNumber, null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, "Failed to delete vehicle: " + e.getMessage(), null));
        }
    }
}