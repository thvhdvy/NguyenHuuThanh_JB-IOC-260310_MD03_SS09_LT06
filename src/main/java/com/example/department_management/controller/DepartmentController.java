package com.example.department_management.controller;


import com.example.department_management.model.dto.request.CreateDepartmentRequest;
import com.example.department_management.model.dto.response.ApiResponse;
import com.example.department_management.model.dto.response.DepartmentResponse;
import com.example.department_management.model.entity.Department;
import com.example.department_management.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<DepartmentResponse>>> getAllDepartments() {
        return ResponseEntity.ok(new ApiResponse<List<DepartmentResponse>>(true, "department list found", departmentService.getAllDepartments()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DepartmentResponse>> createDepartment(@Valid @RequestBody CreateDepartmentRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(true, "department created", departmentService.createDepartment(request)));
    }
}
