package com.dexter.attendanceSystem.model.Response;


import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder

public class StudentCoursesResponse {

    private Long totalCourses;
    private List<StudentCourseResponse> studentCourseResponses;
}
