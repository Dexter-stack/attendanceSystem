package com.dexter.attendanceSystem.serviceImpl;

import com.dexter.attendanceSystem.audit.AppAuditAware;
import com.dexter.attendanceSystem.entity.AppUser;
import com.dexter.attendanceSystem.entity.Attendance;
import com.dexter.attendanceSystem.entity.Course;
import com.dexter.attendanceSystem.exception.StudentException;
import com.dexter.attendanceSystem.model.Response.AttendanceResponse;
import com.dexter.attendanceSystem.repository.AttendanceRepository;
import com.dexter.attendanceSystem.service.AttendanceService;
import com.dexter.attendanceSystem.utils.Errors;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendanceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final AppAuditAware auditAware;

    @Override
    public AttendanceResponse clockIn(String courseId, Date currentDate) {
        AppUser user = auditAware.getCurrentUserAuditor().orElseThrow(() -> new StudentException(Errors.UNAUTHORIZED));


        Attendance attendance = Attendance.builder()
                .courseId(courseId)
                .signInTime(currentDate)
                .user(user)
                .build();
        try {

            attendanceRepository.save(attendance);

        } catch (Exception exception) {
            // log error
            throw new StudentException(Errors.ERROR_OCCUR_WHILE_PERFORMING);
        }


        return AttendanceResponse.builder()
                .CourseId(attendance.getCourseId())
                .clockInTime(attendance.getSignInTime())
                .userId(attendance.getUser().getStudentId())

                .build();
    }

    @Override
    public AttendanceResponse clockOut(String courseId, Date currentDate) {

        AppUser user =  auditAware.getCurrentUserAuditor().orElseThrow(()->new StudentException(Errors.UNAUTHORIZED));
        Optional<Attendance> attendance =  attendanceRepository.findAttendanceByUserIdAndDate(user.getId(), currentDate);
        if(attendance.isPresent()){
            Attendance updateAttendance = attendance.get();
            updateAttendance.setSignOutTime(currentDate);
            try{

                attendanceRepository.save(updateAttendance);
                return AttendanceResponse.builder()
                        .CourseId(updateAttendance.getCourseId())
                        .clockOutTime(updateAttendance.getSignOutTime())
                        .clockInTime(updateAttendance.getSignInTime())
                        .userId(user.getStudentId())
                        .build();

            }catch (Exception  exception){
                // log error
                throw new StudentException(exception.getMessage());
            }
        }
        throw new StudentException(Errors.STUDENT_NEEDS_TO_CLOCKIN);

    }
}
