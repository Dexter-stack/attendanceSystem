package com.dexter.attendanceSystem.serviceImpl;

import com.dexter.attendanceSystem.config.jwtConfig.JwtService;
import com.dexter.attendanceSystem.entity.AppUser;
import com.dexter.attendanceSystem.exception.StudentException;
import com.dexter.attendanceSystem.model.Request.LoginRequest;
import com.dexter.attendanceSystem.model.Request.SignupRequest;
import com.dexter.attendanceSystem.model.Response.LoginResponse;
import com.dexter.attendanceSystem.model.Response.SignUpResponse;
import com.dexter.attendanceSystem.model.Response.UserResponse;
import com.dexter.attendanceSystem.repository.UserRepository;
import com.dexter.attendanceSystem.service.UnathenticatedService;
import com.dexter.attendanceSystem.utils.Errors;
import com.dexter.attendanceSystem.utils.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UnauthenticatedServiceImpl implements UnathenticatedService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    @Transactional
    public SignUpResponse saveUser(SignupRequest request) {
        boolean checkStudentId = userRepository.existsByStudentId(request.getStudentId());
        if (checkStudentId)
            throw new StudentException(Errors.DUPLICATE_USER);

        AppUser appUser = AppUser.builder()
                .studentId(request.getStudentId())
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

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {

        AppUser user = userRepository.findByStudentId(loginRequest.getStudentId()).orElseThrow(() -> new StudentException(Errors.USER_DOES_NOT_EXIST));

        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()))
            throw new StudentException(Errors.INVALID_PASSWORD);
        try {

            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getStudentId(), loginRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            var userObject = userRepository.findByStudentId(loginRequest.getStudentId());
            AppUser appUser = userObject.orElseGet(() -> new AppUser());
            UserResponse userResponse =  UserResponse.builder().student_id(appUser.getStudentId()).role(appUser.getRole()).build();
            return LoginResponse.builder()
                    .userResponse(userResponse)
                    .token(jwtService.generateToken(appUser))
                    .build();

        } catch (Exception exception) {
            // log the error
            throw new StudentException(Errors.ERROR_OCCUR_WHILE_PERFORMING );
        }

    }


}
