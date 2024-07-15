package com.dexter.attendanceSystem.serviceImpl;

import com.dexter.attendanceSystem.entity.AppUser;
import com.dexter.attendanceSystem.exception.StudentException;
import com.dexter.attendanceSystem.model.Request.SignupRequest;
import com.dexter.attendanceSystem.model.Response.SignUpResponse;
import com.dexter.attendanceSystem.model.Response.UserResponse;
import com.dexter.attendanceSystem.repository.UserRepository;
import com.dexter.attendanceSystem.service.UnathenticatedService;
import com.dexter.attendanceSystem.utils.Errors;
import com.dexter.attendanceSystem.utils.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UnauthenticatedServiceImpl implements UnathenticatedService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public SignUpResponse saveUser(SignupRequest request) {
        boolean checkStudentId = userRepository.existsByStudentId(request.getStudent_id());
        if (checkStudentId)
            throw new StudentException(Errors.DUPLICATE_USER);

        AppUser appUser = AppUser.builder()
                .studentId(request.getStudent_id())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        UserResponse response = UserResponse.builder()
                .student_id(appUser.getStudentId())
                .role(Role.USER)
                .build();

        try {

            userRepository.save(appUser);


        } catch (StudentException exception) {
            throw new StudentException(Errors.ERROR_OCCUR_WHILE_PERFORMING);
        }


        return SignUpResponse.builder().userResponse(response).build();
    }
}
