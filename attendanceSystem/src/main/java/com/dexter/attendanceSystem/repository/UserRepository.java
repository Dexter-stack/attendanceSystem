package com.dexter.attendanceSystem.repository;

import com.dexter.attendanceSystem.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser,Long> {


  boolean  existsByStudentId(String studentId);

  Optional<AppUser>findByStudentId(String userId);

}
