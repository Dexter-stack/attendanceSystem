package com.dexter.attendanceSystem.entity;

import com.dexter.attendanceSystem.utils.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long Id;


    @Column(length = 5,nullable = false)
    @NotBlank(message = "Registration  field is required")
    private String student_id;

    @Column(length = 100)
    @NotBlank(message = "Password is required")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE,CascadeType.REMOVE})
    @JoinTable(
            name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id",
                    referencedColumnName = "Id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "course_id",
                    referencedColumnName = "CourseId"
            )


    )

    private List<Course> courses;


}
