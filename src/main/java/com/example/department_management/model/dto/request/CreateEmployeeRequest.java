package com.example.department_management.model.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateEmployeeRequest {
    @NotBlank(message = "full name is required")
    private String fullName;
    @NotBlank(message = "email is required")
    @Email
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^(03|05|07|08|09)\\d{8}$",
            message = "Phone number must have 10 digits and start with 03, 05, 07, 08 or 09"
    )
    private String phone;

    @NotNull(message = "salary required")
    @Positive
    private Double salary;

    @NotNull(message = "department id is required")
    private Long departmentId;
}
