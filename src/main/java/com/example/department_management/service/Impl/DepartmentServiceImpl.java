package com.example.department_management.service.Impl;

import com.example.department_management.exception.DuplicateResourceException;
import com.example.department_management.model.dto.request.CreateDepartmentRequest;
import com.example.department_management.model.dto.response.DepartmentResponse;
import com.example.department_management.model.entity.Department;
import com.example.department_management.repository.DepartmentRepository;
import com.example.department_management.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;


    @Override
    public List<DepartmentResponse> getAllDepartments() {
        return departmentRepository.findAll().stream().map(
                d -> new DepartmentResponse(
                        d.getId(),
                        d.getName(),
                        d.getDescription()
                )
        ).toList();
    }

    @Override
    public DepartmentResponse createDepartment(CreateDepartmentRequest request) {
        if(departmentRepository.findByName(request.getName()) != null)
        {
            throw new DuplicateResourceException(request.getName() + " already exists");
        }
        Department d = new Department();
        d.setName(request.getName());
        d.setDescription(request.getDescription());
        Department savedDepartment = departmentRepository.save(d);
        return new DepartmentResponse(savedDepartment.getId(), savedDepartment.getName(), savedDepartment.getDescription());
    }
}
