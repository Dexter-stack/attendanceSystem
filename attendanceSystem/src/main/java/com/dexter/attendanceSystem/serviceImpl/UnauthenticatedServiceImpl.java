package com.dexter.attendanceSystem.serviceImpl;

import com.dexter.attendanceSystem.entity.AppUser;
import com.dexter.attendanceSystem.model.Request.SignupRequest;
import com.dexter.attendanceSystem.model.Response.SignUpResponse;
import com.dexter.attendanceSystem.model.Response.UserResponse;
import com.dexter.attendanceSystem.repository.UserRepository;
import com.dexter.attendanceSystem.service.UnathenticatedService;
import com.dexter.attendanceSystem.utils.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UnauthenticatedServiceImpl implements UnathenticatedService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SignUpResponse saveUser(SignupRequest request) {

        AppUser appUser = AppUser.builder()
                .student_id(request.getStudent_id())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                   .build();


        UserResponse response = UserResponse.builder()
                .student_id(appUser.getStudent_id())
                .role(Role.USER)
                .build();
         userRepository.save(appUser);



        return SignUpResponse.builder().userResponse(response).build();
    }
}
