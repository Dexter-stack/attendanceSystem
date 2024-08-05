package com.dexter.attendanceSystem.service;

import com.dexter.attendanceSystem.model.Response.AttendanceResponse;
import com.dexter.attendanceSystem.model.Response.AttendancesResponse;

import java.util.Date;

public interface AttendanceService {

    public AttendanceResponse clockIn(String courseId, Date currentDate);
    public AttendanceResponse clockOut(String courseId, Date currentDate);

    public AttendancesResponse fetchAttendanceByDate(int daysAgo);
}
