package com.example.department_management.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CandidateApplyRequest {
    @NotBlank
    private String name;
    @Email
    private String email;
    @NotNull
    private MultipartFile cvFile;
}
