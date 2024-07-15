package com.dexter.attendanceSystem.model.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseRequest {

    @NotBlank(message = "Course field is required")
    private String course;
    @Min(value = 1, message = "Duration field must be greater than 0")
    private int duration;
}
