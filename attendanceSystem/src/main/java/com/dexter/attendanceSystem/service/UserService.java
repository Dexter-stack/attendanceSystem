package com.dexter.attendanceSystem.service;

import com.dexter.attendanceSystem.model.Response.UserResponse;
import com.dexter.attendanceSystem.model.Response.UsersResponse;

public interface UserService {

    public UserResponse fetchSingleUserById(String Id);
    public UsersResponse fetchAllUser();

    public UserResponse  fetchUserByFirstName(String firstName);
}
