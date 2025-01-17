package com.dexter.attendanceSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long Id;
    @Column(nullable = false)
    private String courseId;
    @Column(nullable = true)
    private Date signInTime;
    @Column(nullable = true)
    private Date signOutTime;



    @ManyToOne(fetch = FetchType.EAGER)

    @JoinColumn(
            name = "user_id",
            referencedColumnName = "Id"
    )
    @JsonIgnore
    private AppUser user;




}
