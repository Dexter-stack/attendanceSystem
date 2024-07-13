package com.dexter.attendanceSystem.repository;

import com.dexter.attendanceSystem.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance,Long> {
}
