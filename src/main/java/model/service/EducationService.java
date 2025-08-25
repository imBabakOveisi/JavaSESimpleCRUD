package model.service;

import lombok.Getter;
import model.entity.Education;
import model.repository.EducationRepository;

import java.util.Collections;
import java.util.List;

public class EducationService implements Service<Education, Integer> {

    @Getter
    private final static EducationService service = new EducationService();

    private EducationService() {
    }

    @Override
    public void save(Education education) throws Exception {
        try (EducationRepository educationRepository = new EducationRepository()) {
            educationRepository.save(education);
        }
    }

    @Override
    public void update(Education education) throws Exception {
        try (EducationRepository educationRepository = new EducationRepository()) {
            educationRepository.update(education);
        }
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        try (EducationRepository educationRepository = new EducationRepository()) {
            educationRepository.deleteById(id);
        }
    }

    @Override
    public List<Education> findAll() throws Exception {
        try (EducationRepository educationRepository = new EducationRepository()) {
            return educationRepository.findAll();
        }
    }

    @Override
    public Education findById(Integer id) throws Exception {
        try (EducationRepository educationRepository = new EducationRepository()) {
            return educationRepository.findById(id);
        }
    }

    public List<Education> findByUniversityAndEducationGrade(String university, String educationGrade) throws Exception {
        try (EducationRepository educationRepository = new EducationRepository()) {
            return educationRepository.findByUniversityAndEducationGrade(university, educationGrade);
        }
    }
}
