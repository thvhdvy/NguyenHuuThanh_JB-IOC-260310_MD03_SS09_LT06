package com.example.department_management.service.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.department_management.exception.DuplicateResourceException;
import com.example.department_management.exception.ResourceNotFoundException;
import com.example.department_management.model.dto.request.CandidateApplyRequest;
import com.example.department_management.model.dto.response.CandidateResponse;
import com.example.department_management.model.dto.response.DepartmentResponse;
import com.example.department_management.model.entity.Candidate;
import com.example.department_management.repository.CandidateRepository;
import com.example.department_management.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<CandidateResponse> getAllCandidates() {
        return candidateRepository.findAll().stream().map(
                c -> new CandidateResponse(
                        c.getId(),
                        c.getName(),
                        c.getEmail(),
                        c.getCvUrl()
                )
        ).toList();
    }

    @Override
    public CandidateResponse createCandidateApply(CandidateApplyRequest request) {
        Candidate candidate = candidateRepository.findByEmail(request.getEmail());
        if (candidate != null) {
            throw new DuplicateResourceException("email existed");
        }
        if(request.getCvFile() == null || request.getCvFile().isEmpty()) {
            throw new IllegalArgumentException("CV not found");
        }
        String fileName = request.getCvFile().getOriginalFilename();
        assert fileName != null;
        String extension = fileName.substring(fileName.lastIndexOf('.'));
        ArrayList<String> extensions = new ArrayList<String>();
        extensions.add(".pdf");
        if (!extensions.contains(extension))
        {
            throw new IllegalArgumentException("Invalid file format (PDF)!");
        }
        Map uploadResult;
        try {
            uploadResult = cloudinary.uploader().upload(
                    request.getCvFile().getBytes(),
                    ObjectUtils.emptyMap()
            );
        } catch (IOException e) {
            throw new RuntimeException("Upload avatar failed.");
        }
        String cvUrl = uploadResult.get("secure_url").toString();
        candidate = new Candidate();
        candidate.setEmail(request.getEmail());
        candidate.setName(request.getName());
        candidate.setCvUrl(cvUrl);
        Candidate savedCandidate = candidateRepository.save(candidate);
        return CandidateResponse.builder()
                .id(savedCandidate.getId())
                .name(savedCandidate.getName())
                .email(savedCandidate.getEmail())
                .cvUrl(savedCandidate.getCvUrl())
                .build();
    }
}
