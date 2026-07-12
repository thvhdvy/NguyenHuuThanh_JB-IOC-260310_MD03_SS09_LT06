package com.example.department_management.model.dto.response;

import com.example.department_management.model.entity.Department;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeResponse {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private Double salary;
    private String avatarUrl;
    private DepartmentResponse department;

}
