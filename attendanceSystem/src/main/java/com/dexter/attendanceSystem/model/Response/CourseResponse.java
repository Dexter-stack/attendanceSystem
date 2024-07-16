package com.dexter.attendanceSystem.model.Response;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CourseResponse {


    private String course;

    private Long CourseId;
    private int duration;


}
