package com.dexter.attendanceSystem.model.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpResponse {

    private UserResponse userResponse;
    private String message;




}
