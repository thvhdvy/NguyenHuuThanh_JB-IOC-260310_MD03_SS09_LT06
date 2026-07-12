package com.example.department_management.repository;

import com.example.department_management.model.entity.Employee;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmail(@NotBlank(message = "email is required") @Email String email);
}
