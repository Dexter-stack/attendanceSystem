package com.dexter.attendanceSystem.serviceImpl;

import com.dexter.attendanceSystem.audit.AppAuditAware;
import com.dexter.attendanceSystem.entity.AppUser;
import com.dexter.attendanceSystem.entity.Attendance;
import com.dexter.attendanceSystem.entity.Course;
import com.dexter.attendanceSystem.exception.StudentException;
import com.dexter.attendanceSystem.model.Response.AttendanceResponse;
import com.dexter.attendanceSystem.model.Response.AttendancesResponse;
import com.dexter.attendanceSystem.model.Response.UserResponse;
import com.dexter.attendanceSystem.repository.AttendanceRepository;
import com.dexter.attendanceSystem.service.AttendanceService;
import com.dexter.attendanceSystem.utils.Errors;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        AppUser user = auditAware.getCurrentUserAuditor().orElseThrow(() -> new StudentException(Errors.UNAUTHORIZED));
        Optional<Attendance> attendance = attendanceRepository.findAttendanceByUserIdAndDate(user.getId(), currentDate);
        if (attendance.isPresent()) {
            Attendance updateAttendance = attendance.get();
            updateAttendance.setSignOutTime(currentDate);
            try {

                attendanceRepository.save(updateAttendance);
                return AttendanceResponse.builder()
                        .CourseId(updateAttendance.getCourseId())
                        .clockOutTime(updateAttendance.getSignOutTime())
                        .clockInTime(updateAttendance.getSignInTime())
                        .userId(user.getStudentId())
                        .build();

            } catch (Exception exception) {
                // log error
                throw new StudentException(exception.getMessage());
            }
        }
        throw new StudentException(Errors.STUDENT_NEEDS_TO_CLOCKIN);

    }

    @Override
    public AttendancesResponse fetchAttendanceByDate(int daysAgo) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -daysAgo);
        Date startDate = calendar.getTime();
        Date endDate = new Date(); // current Date



        List<AttendanceResponse> attendanceResponses = attendanceRepository.findAttendanceByDate(startDate, endDate).stream().map(
                attendance -> {

                    UserResponse userResponse = UserResponse.builder()
                            .lastName(attendance.getUser().getLastName())
                            .firstName(attendance.getUser().getFirstName())
                            .role(attendance.getUser().getRole())
                            .student_id(attendance.getUser().getStudentId())
                            .build();
                    AttendanceResponse response = AttendanceResponse.builder()
                            .userResponse(userResponse)
                            .clockOutTime(attendance.getSignOutTime())
                            .clockOutTime(attendance.getSignOutTime())
                            .CourseId(attendance.getCourseId())
                            .userId(attendance.getUser().getStudentId())
                            .build();
                    return response;
                }).collect(Collectors.toList());

        return AttendancesResponse.builder()
                .attendanceResponses(attendanceResponses)
                .total_size((long) attendanceResponses.size())
                .build();


    }
}
