package com.dexter.attendanceSystem.model.Response;


import com.dexter.attendanceSystem.entity.AppUser;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class AttendanceResponse {




    private String CourseId;
    private String userId;
    private Date clockInTime;
    private Date clockOutTime;
    private UserResponse userResponse;
}
