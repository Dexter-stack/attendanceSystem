package com.dexter.attendanceSystem.model.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {
    @NotBlank(message = "user id is required")
    private String studentId;
    @NotBlank(message = "Password field is required")
    private String password;
    @NotBlank(message = "First Name is Required")
    private String firstName;
    @NotBlank(message = "Last Name is required")
    private String lastName;
}
