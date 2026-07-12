package com.example.department_management.controller;

import com.example.department_management.model.dto.request.CreateDepartmentRequest;
import com.example.department_management.model.dto.request.CreateEmployeeRequest;
import com.example.department_management.model.dto.response.ApiResponse;
import com.example.department_management.model.dto.response.DepartmentResponse;
import com.example.department_management.model.dto.response.EmployeeResponse;
import com.example.department_management.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<EmployeeResponse>>> getAllEmployees() {
        return ResponseEntity.ok(new ApiResponse<>(true, "employees list found", employeeService.getAllEmployees()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeResponse>> createDepartment(@Valid @RequestBody CreateEmployeeRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(true, "employees created", employeeService.createEmployee(request)));
    }

    @PutMapping("/{id}/avatar")
    public ResponseEntity<ApiResponse<EmployeeResponse>> updateDepartment(@PathVariable Long id, @ModelAttribute MultipartFile avatar) {
        return ResponseEntity.ok(new ApiResponse<>(true, "avatar updated", employeeService.updateAvatar(id, avatar)));
    }
}
