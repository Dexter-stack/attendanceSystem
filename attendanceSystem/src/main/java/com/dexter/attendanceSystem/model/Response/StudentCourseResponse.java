package com.dexter.attendanceSystem.model.Response;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder

public class StudentCourseResponse {

    private Long courseId;
    private String courseName;
    private int duration;
}
