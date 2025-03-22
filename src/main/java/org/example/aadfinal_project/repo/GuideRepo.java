package org.example.aadfinal_project.repo;

import org.example.aadfinal_project.entity.Guide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuideRepo extends JpaRepository<Guide, Integer> {

    /**
     * Find guides by name containing the given string (case-insensitive)
     * @param name Name to search
     * @return List of matching guides
     */
    List<Guide> findByNameContainingIgnoreCase(String name);

    /**
     * Find all guides with their guided tours eagerly loaded
     * @return List of guides with guided tours
     */
    @Query("SELECT DISTINCT g FROM Guide g LEFT JOIN FETCH g.guideTours")
    List<Guide> findAllWithTours();

    /**
     * Find guide by id with guided tours eagerly loaded
     * @param id Guide ID
     * @return Guide with guided tours
     */
    @Query("SELECT g FROM Guide g LEFT JOIN FETCH g.guideTours WHERE g.id = :id")
    Guide findByIdWithTours(Integer id);
}