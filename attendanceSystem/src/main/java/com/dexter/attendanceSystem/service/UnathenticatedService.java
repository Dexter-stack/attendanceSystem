package com.dexter.attendanceSystem.service;

import com.dexter.attendanceSystem.entity.AppUser;
import com.dexter.attendanceSystem.model.Request.SignupRequest;
import com.dexter.attendanceSystem.model.Response.SignUpResponse;

public interface UnathenticatedService {

    SignUpResponse saveUser(SignupRequest request);
}
