package org.example.aadfinal_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDTO {
    private String registrationNumber;
    private String type; // CAR, BUS, VAN
    private Integer capacity;
    private Boolean available;
    private Double pricePerDay;
}
