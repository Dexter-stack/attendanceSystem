package com.dexter.attendanceSystem.model.Response;

import com.dexter.attendanceSystem.utils.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Role role;
    private String student_id;
}
