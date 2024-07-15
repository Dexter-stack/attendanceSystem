package com.dexter.attendanceSystem.repository;

import com.dexter.attendanceSystem.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser,Long> {


  boolean  existsByStudentId(String studentId);
}
