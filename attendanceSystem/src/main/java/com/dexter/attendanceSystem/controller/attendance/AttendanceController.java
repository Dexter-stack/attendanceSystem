package com.dexter.attendanceSystem.controller.attendance;

import com.dexter.attendanceSystem.service.AttendanceService;
import com.dexter.attendanceSystem.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;


    @PostMapping("/clock-in")
    public ResponseEntity<ApiResponse> signIn(@RequestParam("courseId") String courseId, HttpServletRequest httpServletRequest){
        ApiResponse response =  ApiResponse.builder()

                .data(attendanceService.clockIn(courseId,new Date()))
                .path(httpServletRequest.getRequestURI())
                .status(HttpStatus.CREATED.value())
                .isSuccessful(true)
                .build();
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }


    @PutMapping("/clock-out")
    public ResponseEntity<ApiResponse> signOut(@RequestParam("courseId") String courseId, HttpServletRequest httpServletRequest){
        ApiResponse response = ApiResponse.builder()
                .data(attendanceService.clockOut(courseId, new Date()))
                .status(HttpStatus.CREATED.value())
                .isSuccessful(true)
                .path(httpServletRequest.getRequestURI())
                .build();
        return  new ResponseEntity<>(response,HttpStatus.CREATED);
    }

}
