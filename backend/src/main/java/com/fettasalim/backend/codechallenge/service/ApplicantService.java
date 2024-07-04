package com.fettasalim.backend.codechallenge.service;

import com.fettasalim.backend.codechallenge.model.Applicant;
import com.fettasalim.backend.codechallenge.repository.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicantService {

    @Autowired
    private ApplicantRepository applicantRepository;

    public Applicant addApplicant(Applicant applicant) {
        applicant.setStatus(Applicant.Status.OPEN);
        return applicantRepository.save(applicant);
    }

    public List<Applicant> getAllApplicants(String name, Applicant.Status status) {
        if (name != null && status != null) {
            return applicantRepository.findByFirstNameContainingIgnoreCaseAndStatus(name, status);
        } else if (name != null) {
            return applicantRepository.findByFirstNameContainingIgnoreCase(name);
        } else if (status != null) {
            return applicantRepository.findByStatus(status);
        } else {
            return applicantRepository.findAll();
        }
    }
/* find applicant by id dont needed now
    public Optional<Applicant> getApplicantById(String id) {
        return applicantRepository.findById(id);
    }*/

    public void updateApplicantStatus(String id, Applicant.Status status) {
        Optional<Applicant> applicantOptional = applicantRepository.findById(id);
        if (applicantOptional.isPresent()) {
            Applicant applicant = applicantOptional.get();
            applicant.setStatus(status);
            applicantRepository.save(applicant);
        }
    }
}
