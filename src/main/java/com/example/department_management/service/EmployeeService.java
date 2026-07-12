package com.example.department_management.service;

import com.example.department_management.model.dto.request.CreateDepartmentRequest;
import com.example.department_management.model.dto.request.CreateEmployeeRequest;
import com.example.department_management.model.dto.response.DepartmentResponse;
import com.example.department_management.model.dto.response.EmployeeResponse;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponse> getAllEmployees();
    EmployeeResponse createEmployee(@Valid CreateEmployeeRequest request);
    EmployeeResponse updateAvatar(Long id, MultipartFile avatar);
}
