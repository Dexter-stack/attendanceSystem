package com.dexter.attendanceSystem.serviceImpl;

import com.dexter.attendanceSystem.entity.Course;
import com.dexter.attendanceSystem.exception.CourseException;
import com.dexter.attendanceSystem.exception.StudentException;
import com.dexter.attendanceSystem.model.Request.CourseRequest;
import com.dexter.attendanceSystem.model.Response.CourseResponse;
import com.dexter.attendanceSystem.repository.CourseRepository;
import com.dexter.attendanceSystem.service.CourseService;
import com.dexter.attendanceSystem.utils.Errors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
