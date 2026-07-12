package com.example.department_management.repository;

import com.example.department_management.model.entity.Candidate;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Candidate findByEmail(@Email String email);
}
