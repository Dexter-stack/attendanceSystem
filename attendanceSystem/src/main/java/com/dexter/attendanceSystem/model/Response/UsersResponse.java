package com.dexter.attendanceSystem.model.Response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UsersResponse {

    private List<UserResponse> users;
    private Long total;
}
