package com.example.department_management.model.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CandidateResponse {
    private Long id;
    private String name;
    private String email;
    private String cvUrl;
}
