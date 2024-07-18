package com.dexter.attendanceSystem.repository;

import com.dexter.attendanceSystem.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance,Long> {

    @Query("SELECT a FROM Attendance a WHERE a.user.id = :userId AND DATE(a.signInTime) = DATE(:date)")
    Optional<Attendance> findAttendanceByUserIdAndDate(@Param("userId") Long userId, @Param("date") Date date);
}
