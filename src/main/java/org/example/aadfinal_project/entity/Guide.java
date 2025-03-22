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
@Table(name = "guides")
public class Guide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String contact;
    private String languages; // Comma-separated: "English, Spanish, French"
    private Double rating;

    @OneToMany(mappedBy = "guide", cascade = CascadeType.ALL)
    private List<GuideTour> guidedTours;

    // Getters & Setters
}
