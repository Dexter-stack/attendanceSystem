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
    private String student_id;


    @NotBlank(message = "Password is required")
    private String password;


}
