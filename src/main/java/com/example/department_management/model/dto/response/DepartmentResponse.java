package com.example.department_management.model.dto.response;

import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class DepartmentResponse {
    private Long id;
    private String name;
    private String description;
}
