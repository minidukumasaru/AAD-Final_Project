package org.example.aadfinal_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "travel_packages")
public class TravelPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String destination;
    private Double price;
    private String description;
    private Integer duration; // Days
    private Boolean active;

    @OneToMany(mappedBy = "travelPackage", cascade = CascadeType.ALL)
    private List<PackageBooking> packageBookings;

    @OneToMany(mappedBy = "travelPackage", cascade = CascadeType.ALL)
    private List<OfferAssignment> offers;

    @OneToMany(mappedBy = "travelPackage", cascade = CascadeType.ALL)
    private List<VehicleBooking> vehicleBookings;

    // Getters & Setters
}
