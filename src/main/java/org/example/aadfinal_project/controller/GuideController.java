package org.example.aadfinal_project.controller;

import org.example.aadfinal_project.entity.Guide;
import org.example.aadfinal_project.service.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guides")
public class GuideController {

    private final GuideService guideService;

    @Autowired
    public GuideController(GuideService guideService) {
        this.guideService = guideService;
    }

    @GetMapping
    public ResponseEntity<List<Guide>> getAllGuides() {
        List<Guide> guides = guideService.getAllGuides();
        return ResponseEntity.ok(guides);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Guide> getGuideById(@PathVariable int id) {
        return guideService.getGuideById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Guide>> searchGuidesByName(@RequestParam String name) {
        List<Guide> guides = guideService.searchGuidesByName(name);
        return ResponseEntity.ok(guides);
    }

    @PostMapping
    public ResponseEntity<Guide> createGuide(@RequestBody Guide guide) {
        Guide savedGuide = guideService.saveGuide(guide);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGuide);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Guide> updateGuide(@PathVariable int id, @RequestBody Guide guide) {
        guide.setId(id); // Ensure the ID is set
        Guide updatedGuide = guideService.saveGuide(guide);
        return ResponseEntity.ok(updatedGuide);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuide(@PathVariable int id) {
        guideService.deleteGuide(id);
        return ResponseEntity.noContent().build();
    }
}