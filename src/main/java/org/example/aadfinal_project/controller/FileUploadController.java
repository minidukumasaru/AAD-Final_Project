package org.example.aadfinal_project.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {

    @Value("${file.upload-dir:./uploads/images}")
    private String uploadDir;

    @PostMapping("/guide-image")
    public ResponseEntity<?> uploadGuideImage(@RequestParam("image") MultipartFile file) {
        try {
            // Create directory if it doesn't exist
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Generate a unique filename
            String filename = UUID.randomUUID().toString() + getFileExtension(file.getOriginalFilename());
            Path filePath = Paths.get(uploadDir, filename);

            // Save the file
            Files.write(filePath, file.getBytes());

            // Return the path to be stored in the database
            String storedPath = "/images/" + filename;

            Map<String, String> response = new HashMap<>();
            response.put("imagePath", storedPath);

            return ResponseEntity.ok(response);
        } catch (IOException e) {
            e.printStackTrace();
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Failed to upload image: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    private String getFileExtension(String filename) {
        if (filename == null) return ".jpg";
        int lastDotIndex = filename.lastIndexOf(".");
        if (lastDotIndex < 0) return ".jpg";
        return filename.substring(lastDotIndex);
    }
}