package org.example.aadfinal_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GuideDTO {
    private String name;
    private String contact;
    private String languages; // Comma-separated: "English, Spanish, French"
    private Double rating;
    private Boolean available;
    private String imagePath;
}
