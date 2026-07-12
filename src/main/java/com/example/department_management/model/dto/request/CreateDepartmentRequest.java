package com.example.department_management.model.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class CreateDepartmentRequest {
    @NotBlank(message = "name not blank")
    @Size(min = 5, max = 50, message = "5-50 character")
    private String name;

    @Size(max = 100, message = "Max 100 character")
    private String description;
}
