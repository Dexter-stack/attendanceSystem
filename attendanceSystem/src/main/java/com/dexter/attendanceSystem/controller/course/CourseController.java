package com.dexter.attendanceSystem.controller.course;

import com.dexter.attendanceSystem.model.Request.CourseRequest;
import com.dexter.attendanceSystem.service.CourseService;
import com.dexter.attendanceSystem.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/course/")

public class CourseController {

    private  final CourseService courseService;


    @PostMapping("/saveCourse")
    public ResponseEntity<ApiResponse> saveCourse(@RequestBody @Valid CourseRequest courseRequest, HttpServletRequest httpServletRequest){

    ApiResponse response = ApiResponse.builder()
            .data(courseService.saveCourse(courseRequest))
            .path(httpServletRequest.getRequestURI())
            .isSuccessful(true)
            .status(HttpStatus.OK.value())
            .build();
    return new ResponseEntity<>(response,HttpStatus.OK);


    }


    @GetMapping("/courses")
    public ResponseEntity<ApiResponse> fetchAllCourses(HttpServletRequest httpServletRequest){
        ApiResponse response =  ApiResponse.builder()
                .data(courseService.getAllCourses())
                .path(httpServletRequest.getRequestURI())
                .isSuccessful(true)
                .status(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @GetMapping("/course")

    public ResponseEntity<ApiResponse> fetchSingleCourseByCourseName(@RequestParam("course") String course, HttpServletRequest httpServletRequest){

        ApiResponse response =  ApiResponse.builder()
                .data(courseService.getCourseByCourseName(course))
                .status(HttpStatus.OK.value())
                .isSuccessful(true)
                .path(httpServletRequest.getRequestURI())
                .build();
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @GetMapping("/course/{id}")
    public ResponseEntity<ApiResponse> fetchSingleCourseById(@PathVariable("id") Long courseId, HttpServletRequest httpServletRequest ){
        ApiResponse response = ApiResponse.builder()
                .data(courseService.getCourseById(courseId))
                .status(HttpStatus.OK.value())
                .path(httpServletRequest.getRequestURI())
                .isSuccessful(true)
                .build();
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }


    @PutMapping("/course/{id}")
    public ResponseEntity<ApiResponse> updateCourse(@PathVariable("id") Long courseId, @RequestBody CourseRequest courseRequest, HttpServletRequest httpServletRequest){

        ApiResponse response = ApiResponse.builder()
                .data(courseService.updateCourse(courseId,courseRequest))
                .status(HttpStatus.OK.value())
                .path(httpServletRequest.getRequestURI())
                .isSuccessful(true)
                .build();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }



}
