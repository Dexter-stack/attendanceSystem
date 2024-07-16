package com.dexter.attendanceSystem.service;

import com.dexter.attendanceSystem.model.Request.CourseRequest;
import com.dexter.attendanceSystem.model.Response.CourseResponse;
import com.dexter.attendanceSystem.model.Response.CoursesResponse;


public interface CourseService {

    public CourseResponse saveCourse(CourseRequest courseRequest);
    public CoursesResponse getAllCourses();
    public CourseResponse  getCourseByCourseName(String course);
}
