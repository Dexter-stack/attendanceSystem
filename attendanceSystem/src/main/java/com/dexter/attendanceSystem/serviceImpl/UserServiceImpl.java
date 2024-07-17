package com.dexter.attendanceSystem.serviceImpl;

import com.dexter.attendanceSystem.entity.AppUser;
import com.dexter.attendanceSystem.exception.StudentException;
import com.dexter.attendanceSystem.model.Response.UserResponse;
import com.dexter.attendanceSystem.model.Response.UsersResponse;
import com.dexter.attendanceSystem.repository.UserRepository;
import com.dexter.attendanceSystem.service.UserService;
import com.dexter.attendanceSystem.utils.Errors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;


    @Override
    public UserResponse fetchSingleUserById(String Id) {
        AppUser user = userRepository.findByStudentId(Id).orElseThrow(() -> new StudentException(Errors.USER_DOES_NOT_EXIST));

        return UserResponse.builder().firstName(user.getFirstName())
                .lastName(user.getLastName())
                .student_id(user.getStudentId())
                .firstName(user.getFirstName())
                .Id(user.getId())
                .role(user.getRole())


                .build();
    }

    @Override
    public UsersResponse fetchAllUser() {

        List<UserResponse> userResponses = userRepository.findAll().stream()
                .map(appUser -> UserResponse.builder()
                        .firstName(appUser.getFirstName())
                        .lastName(appUser.getLastName())
                        .student_id(appUser.getStudentId())
                        .role(appUser.getRole())
                        .Id(appUser.getId())
                        .build()

                ).collect(Collectors.toList());
        return UsersResponse.builder()
                .users(userResponses)
                .total((long) userResponses.size())
                .build();
    }

    @Override
    public UserResponse fetchUserByFirstName(String firstName) {
        AppUser user = userRepository.findByFirstName(firstName).orElseThrow(()-> new StudentException(Errors.USER_DOES_NOT_EXIST));

        return UserResponse.builder()
                .student_id(user.getStudentId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .Id(user.getId())
                .build();
    }
}
