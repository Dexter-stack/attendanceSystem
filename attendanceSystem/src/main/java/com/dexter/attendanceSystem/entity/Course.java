package com.dexter.attendanceSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY

    )
    private Long CourseId;
    @Column(nullable = false)
    @NotBlank(message = "Course field is required")
    private String course;
    @NotBlank(message = "Duration field is required")
    private int duration;


    @ManyToMany(mappedBy = "courses")
    private List<AppUser> appUsers;






}
