package com.dexter.attendanceSystem.service;

import com.dexter.attendanceSystem.entity.AppUser;
import com.dexter.attendanceSystem.model.Request.LoginRequest;
import com.dexter.attendanceSystem.model.Request.SignupRequest;
import com.dexter.attendanceSystem.model.Response.LoginResponse;
import com.dexter.attendanceSystem.model.Response.SignUpResponse;

public interface UnathenticatedService {

    SignUpResponse saveUser(SignupRequest request);
    LoginResponse loginUser(LoginRequest loginRequest);
}
