package com.fettasalim.backend.codechallenge.repository;

import com.fettasalim.backend.codechallenge.model.Applicant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ApplicantRepository extends MongoRepository<Applicant, String> {
    List<Applicant> findByFirstNameContainingIgnoreCaseAndStatus(String firstName, Applicant.Status status);
    List<Applicant> findByFirstNameContainingIgnoreCase(String firstName);
    List<Applicant> findByStatus(Applicant.Status status);
}
