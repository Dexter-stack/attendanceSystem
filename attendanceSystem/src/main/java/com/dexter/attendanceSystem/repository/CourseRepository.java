package com.dexter.attendanceSystem.repository;

import com.dexter.attendanceSystem.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {

}
