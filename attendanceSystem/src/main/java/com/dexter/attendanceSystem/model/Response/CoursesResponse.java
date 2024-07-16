package com.dexter.attendanceSystem.model.Response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CoursesResponse {
    private List<CourseResponse> courses;
    private Long totalCourse;
}
