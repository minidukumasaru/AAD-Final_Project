package org.example.aadfinal_project.service;

import org.example.aadfinal_project.entity.Guide;

import java.util.List;
import java.util.Optional;

public interface GuideService {

    /**
     * Get all guides
     *
     * @return List of all guides
     */
    List<Guide> getAllGuides();

    /**
     * Get guide by ID
     *
     * @param id The guide ID
     * @return Optional containing guide if found
     */
    Optional<Guide> getGuideById(int id);

    /**
     * Search guides by name (case-insensitive partial match)
     *
     * @param name Name to search
     * @return List of matching guides
     */
    List<Guide> searchGuidesByName(String name);

    /**
     * Save a new guide or update an existing one
     *
     * @param guide Guide to save
     * @return Saved guide with ID
     */
    Guide saveGuide(Guide guide);

    /**
     * Delete a guide by ID
     *
     * @param id Guide ID to delete
     */
    void deleteGuide(int id);
}