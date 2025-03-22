package org.example.aadfinal_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Double discountPercentage;
    private LocalDate startDate;
    private LocalDate endDate;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL)
    private List<OfferAssignment> offerAssignments;

    // Getters & Setters
}
