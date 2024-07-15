package com.dexter.attendanceSystem.service;

import com.dexter.attendanceSystem.model.Request.CourseRequest;
import com.dexter.attendanceSystem.model.Response.CourseResponse;


public interface CourseService {

    public CourseResponse saveCourse(CourseRequest courseRequest);
}
