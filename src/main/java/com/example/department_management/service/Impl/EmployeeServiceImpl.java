package com.example.department_management.service.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.department_management.exception.DuplicateResourceException;
import com.example.department_management.exception.ResourceNotFoundException;
import com.example.department_management.model.dto.request.CreateDepartmentRequest;
import com.example.department_management.model.dto.request.CreateEmployeeRequest;
import com.example.department_management.model.dto.response.DepartmentResponse;
import com.example.department_management.model.dto.response.EmployeeResponse;
import com.example.department_management.model.entity.Employee;
import com.example.department_management.repository.DepartmentRepository;
import com.example.department_management.repository.EmployeeRepository;
import com.example.department_management.service.EmployeeService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll().stream().map(
                e -> new EmployeeResponse(
                        e.getId(),
                        e.getFullName(),
                        e.getEmail(),
                        e.getPhone(),
                        e.getSalary(),
                        e.getAvatarUrl(),
                        new DepartmentResponse(
                                e.getDepartment().getId(),
                                e.getDepartment().getName(),
                                e.getDepartment().getDescription()
                        )
                )
        ).toList();
    }

    @Override
    public EmployeeResponse createEmployee(CreateEmployeeRequest request) {
        if(employeeRepository.findByEmail(request.getEmail())!= null)
        {
            throw new DuplicateResourceException("email already exists");
        }
        Employee employee = new Employee();
        employee.setEmail(request.getEmail());
        employee.setFullName(request.getFullName());
        employee.setPhone(request.getPhone());
        employee.setSalary(request.getSalary());
        employee.setDepartment(departmentRepository.findById(request.getDepartmentId()).orElseThrow(()->new ResourceNotFoundException("Department not exist")));
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeResponse.builder()
                .id(savedEmployee.getId())
                .fullName(savedEmployee.getFullName())
                .email(savedEmployee.getEmail())
                .phone(savedEmployee.getPhone())
                .department(
                        new DepartmentResponse(
                                savedEmployee.getDepartment().getId(),
                                savedEmployee.getDepartment().getName(),
                                savedEmployee.getDepartment().getDescription()
                        )
                )
                .salary(savedEmployee.getSalary())
                .avatarUrl(savedEmployee.getAvatarUrl())
                .build();
    }

    @Override
    public EmployeeResponse updateAvatar(Long id, MultipartFile avatar) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        if(avatar == null || avatar.isEmpty())
        {
            throw new ResourceNotFoundException("Avatar not found");
        }
        if(avatar.getSize() > 2 *  1024 * 1024)
        {
            throw new IllegalArgumentException("Avatar too large <= 2MB");
        }
        String fileName = avatar.getOriginalFilename();
        assert fileName != null;
        String extension = fileName.substring(fileName.lastIndexOf('.'));
        ArrayList<String> extensions = new ArrayList<String>();
        extensions.add(".jpg");
        extensions.add(".jpeg");
        extensions.add(".png");
        if (!extensions.contains(extension))
        {
            throw new IllegalArgumentException("Invalid file format (png, jpg, jpeg)");
        }

        Map uploadResult;
        try {
            uploadResult = cloudinary.uploader().upload(
                    avatar.getBytes(),
                    ObjectUtils.emptyMap()
            );
        } catch (IOException e) {
            throw new RuntimeException("Upload avatar failed.");
        }
        String imgUrl = uploadResult.get("secure_url").toString();
        employee.setAvatarUrl(imgUrl);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeResponse.builder()
                .id(savedEmployee.getId())
                .fullName(savedEmployee.getFullName())
                .email(savedEmployee.getEmail())
                .phone(savedEmployee.getPhone())
                .department(
                        new DepartmentResponse(
                                savedEmployee.getDepartment().getId(),
                                savedEmployee.getDepartment().getName(),
                                savedEmployee.getDepartment().getDescription()
                        )
                )
                .salary(savedEmployee.getSalary())
                .avatarUrl(savedEmployee.getAvatarUrl())
                .build();

    }
}

