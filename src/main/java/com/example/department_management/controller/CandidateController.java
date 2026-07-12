package com.example.department_management.controller;

import com.example.department_management.model.dto.request.CandidateApplyRequest;
import com.example.department_management.model.dto.response.ApiResponse;
import com.example.department_management.model.dto.response.CandidateResponse;
import com.example.department_management.model.entity.Candidate;
import com.example.department_management.service.CandidateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CandidateResponse>>> findAll() {
        return ResponseEntity.ok(new ApiResponse<>(true, "candidates list found", candidateService.getAllCandidates()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CandidateResponse>> createCandidateApply(@Valid @ModelAttribute CandidateApplyRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(true, "apply created", candidateService.createCandidateApply(request)));
    }
}
