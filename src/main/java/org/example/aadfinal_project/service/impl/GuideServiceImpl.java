package org.example.aadfinal_project.service.impl;

import org.example.aadfinal_project.entity.Guide;
import org.example.aadfinal_project.repo.GuideRepo;
import org.example.aadfinal_project.service.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GuideServiceImpl implements GuideService {

    private final GuideRepo guideRepo;

    @Autowired
    public GuideServiceImpl(GuideRepo guideRepo) {
        this.guideRepo = guideRepo;
    }

    @Override
    public List<Guide> getAllGuides() {
        return guideRepo.findAll();
    }

    @Override
    public Optional<Guide> getGuideById(int id) {
        return guideRepo.findById(id);
    }

    @Override
    public List<Guide> searchGuidesByName(String name) {
        return guideRepo.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Guide saveGuide(Guide guide) {
        return guideRepo.save(guide);
    }

    @Override
    public void deleteGuide(int id) {
        guideRepo.deleteById(id);
    }
}