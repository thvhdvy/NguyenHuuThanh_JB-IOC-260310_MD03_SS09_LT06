package com.example.department_management.service;

import com.example.department_management.model.dto.request.CandidateApplyRequest;
import com.example.department_management.model.dto.response.CandidateResponse;
import jakarta.validation.Valid;


import java.util.List;

public interface CandidateService {
    List<CandidateResponse> getAllCandidates();

    CandidateResponse createCandidateApply(@Valid CandidateApplyRequest request);
}
