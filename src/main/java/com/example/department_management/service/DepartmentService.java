package com.example.department_management.service;

import com.example.department_management.model.dto.request.CreateDepartmentRequest;
import com.example.department_management.model.dto.response.DepartmentResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface DepartmentService {
    List<DepartmentResponse> getAllDepartments();

    DepartmentResponse createDepartment(@Valid CreateDepartmentRequest request);
}
