package com.dexter.attendanceSystem.repository;

import com.dexter.attendanceSystem.entity.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCourseRepository extends JpaRepository<StudentCourse,Long> {
}
