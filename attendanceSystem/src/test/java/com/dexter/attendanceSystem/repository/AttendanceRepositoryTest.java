package com.dexter.attendanceSystem.repository;

import com.dexter.attendanceSystem.entity.AppUser;
import com.dexter.attendanceSystem.entity.Attendance;
import com.dexter.attendanceSystem.entity.Course;
import com.dexter.attendanceSystem.utils.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class AttendanceRepositoryTest {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private AppUser user;
    private Course course;

    @BeforeEach
    void setUp() {
        user = AppUser.builder()
                .firstName("john")
                .lastName("Doe")
                .role(Role.USER)
                .password("12345")
                .studentId("2234")
                .Id(1L)
                .build();
        testEntityManager.persist(user);

        course = Course.builder()
                .CourseId(1L)
                .duration(30)
                .build();
        testEntityManager.persist(course);
    }

    @Test
    public void clockInTest() {
        // When
        Attendance attendance = Attendance.builder()
                .user(user)
                .signInTime(new Date())
                .courseId(course.getCourseId().toString())
                .build();
        Attendance savedAttendance = attendanceRepository.save(attendance);

        // Then
        Optional<Attendance> foundAttendance = attendanceRepository.findById(savedAttendance.getId());
        assertThat(foundAttendance).isPresent();
        assertThat(foundAttendance.get().getUser().getId()).isEqualTo(1L);
        assertThat(foundAttendance.get().getSignInTime()).isEqualTo(savedAttendance.getSignInTime());
        assertThat(foundAttendance.get().getCourseId()).isEqualTo(course.getCourseId());
    }
}
