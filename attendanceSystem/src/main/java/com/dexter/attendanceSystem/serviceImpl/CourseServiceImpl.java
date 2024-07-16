package com.dexter.attendanceSystem.serviceImpl;

import com.dexter.attendanceSystem.entity.Course;
import com.dexter.attendanceSystem.exception.CourseException;
import com.dexter.attendanceSystem.exception.StudentException;
import com.dexter.attendanceSystem.model.Request.CourseRequest;
import com.dexter.attendanceSystem.model.Response.CourseResponse;
import com.dexter.attendanceSystem.model.Response.CoursesResponse;
import com.dexter.attendanceSystem.repository.CourseRepository;
import com.dexter.attendanceSystem.service.CourseService;
import com.dexter.attendanceSystem.utils.Errors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public CourseResponse saveCourse(CourseRequest courseRequest) {
        boolean checkCourseExists = courseRepository.existsByCourse(courseRequest.getCourse());
        if (checkCourseExists)
            throw new StudentException(Errors.COURSE_ALREADY_EXIST);
        Course course = Course.builder()
                .course(courseRequest.getCourse())
                .duration(courseRequest.getDuration())
                .build();

        try {

            courseRepository.save(course);


        } catch (Exception exception) {

            throw new CourseException(Errors.ERROR_OCCUR_WHILE_PERFORMING + exception);

        }
        return CourseResponse.builder()
                .course(course.getCourse())
                .duration(course.getDuration())
                .build();
    }


    @Override
    public CoursesResponse getAllCourses() {
        List<CourseResponse> courses = courseRepository.findAll().stream().map(
                course -> CourseResponse.builder()
                        .course(course.getCourse())
                        .duration(course.getDuration())
                        .CourseId(course.getCourseId())
                        .build()
        ).collect(Collectors.toList());

        return CoursesResponse.builder()
                .courses(courses)
                .totalCourse((long) courses.size())
                .build();
    }

    @Override
    public CourseResponse getCourseByCourseName(String course) {


        Course courseData = courseRepository.findByCourse(course).orElseThrow(() -> new CourseException(Errors.COURSE_DOES_NOT_EXIST));
        return CourseResponse.builder()
                .CourseId(courseData.getCourseId())
                .course(courseData.getCourse())
                .duration(courseData.getDuration())
                .build();

    }

    @Override

    public CourseResponse getCourseById(Long courseId) {

        Course course = courseRepository.findById(courseId).orElseThrow(() -> new CourseException(Errors.COURSE_DOES_NOT_EXIST));
        return CourseResponse.builder()
                .CourseId(courseId)
                .duration(course.getDuration())
                .course(course.getCourse())
                .build();

    }

    @Override
    public CourseResponse updateCourse(Long courseId, CourseRequest courseRequest) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new CourseException(Errors.COURSE_DOES_NOT_EXIST));
        if (Objects.nonNull(courseRequest.getCourse()) && !"".equalsIgnoreCase(courseRequest.getCourse())) {
            course.setCourse(courseRequest.getCourse());
        }
        if (Objects.nonNull(courseRequest.getDuration()) && !"".equalsIgnoreCase(String.valueOf(courseRequest.getDuration()))) {
            course.setDuration(courseRequest.getDuration());
        }
        try {

            courseRepository.save(course);

        } catch (Exception exception) {
            // log the error
            throw new CourseException(Errors.ERROR_OCCUR_WHILE_PERFORMING);
        }


        return CourseResponse.builder()
                .course(course.getCourse())
                .duration(course.getDuration())
                .CourseId(course.getCourseId())
                .build();
    }




}
