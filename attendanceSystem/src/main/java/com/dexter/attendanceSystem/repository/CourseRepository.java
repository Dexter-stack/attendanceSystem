package com.dexter.attendanceSystem.repository;

import com.dexter.attendanceSystem.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course,Long> {

    public Optional<Course> findByCourse(String course);
    boolean existsByCourse(String course);

}
