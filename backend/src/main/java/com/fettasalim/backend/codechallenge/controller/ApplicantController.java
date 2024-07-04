package com.fettasalim.backend.codechallenge.controller;

import com.fettasalim.backend.codechallenge.model.Applicant;
import com.fettasalim.backend.codechallenge.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins ="http://localhost:8081/")
public class ApplicantController {

    @Autowired
    private ApplicantService applicantService;

    @PostMapping("applicants")
    public Applicant addApplicant(@RequestBody Applicant applicant) {
        return applicantService.addApplicant(applicant);
    }

    @GetMapping("applicants")
    public List<Applicant> getAllApplicants(@RequestParam(required = false) String name,
                                            @RequestParam(required = false) Applicant.Status status) {
        return applicantService.getAllApplicants(name, status);
    }

    @PatchMapping("/{id}/accept")
    public void acceptApplicant(@PathVariable String id) {
        applicantService.updateApplicantStatus(id, Applicant.Status.ACCEPTED);
    }

    @PatchMapping("/{id}/decline")
    public void declineApplicant(@PathVariable String id) {
        applicantService.updateApplicantStatus(id, Applicant.Status.DECLINED);
    }
}
