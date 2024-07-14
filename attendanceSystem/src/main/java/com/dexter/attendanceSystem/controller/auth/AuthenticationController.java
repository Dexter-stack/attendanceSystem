package com.dexter.attendanceSystem.controller.auth;

import com.dexter.attendanceSystem.model.Request.SignupRequest;
import com.dexter.attendanceSystem.service.UnathenticatedService;

import com.dexter.attendanceSystem.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")

public class AuthenticationController {

    private final UnathenticatedService unauthenticatedService;

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse> signUp( @RequestBody @Valid SignupRequest signupRequest, HttpServletRequest httpServletRequest) {
        ApiResponse response = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(unauthenticatedService.saveUser(signupRequest))
                .isSuccessful(true)
                .path(httpServletRequest.getRequestURI())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
