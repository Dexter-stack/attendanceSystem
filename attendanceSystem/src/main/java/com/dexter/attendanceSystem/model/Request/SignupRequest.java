package com.dexter.attendanceSystem.model.Request;


import com.dexter.attendanceSystem.utils.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
public class SignupRequest {
    @NotBlank(message = "Registration  field is required")
    private String studentId;


    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "First Name is Required")
    private String firstName;
    @NotBlank(message = "Last Name is required")
    private String lastName;

    private Role role;


}
