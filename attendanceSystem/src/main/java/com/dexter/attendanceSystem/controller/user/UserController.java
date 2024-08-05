package com.dexter.attendanceSystem.controller.user;


import com.dexter.attendanceSystem.entity.AppUser;
import com.dexter.attendanceSystem.service.UserService;
import com.dexter.attendanceSystem.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;


    @GetMapping("/user")
    public ResponseEntity<ApiResponse> fetchSingleStudentById(@RequestParam("id") String id, HttpServletRequest httpServletRequest){
        ApiResponse response =  ApiResponse.builder()
                .data(userService.fetchSingleUserById(id))
                .isSuccessful(true)
                .status(HttpStatus.OK.value())
                .path(httpServletRequest.getRequestURI())
                .build();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    @GetMapping("/user/firstName")

    public ResponseEntity<ApiResponse> fetchSingleStudentByFirstName(@RequestParam("firstName") String firstName, HttpServletRequest httpServletRequest){
        ApiResponse response =  ApiResponse.builder()
                .data(userService.fetchUserByFirstName(firstName))
                .status(HttpStatus.OK.value())
                .path(httpServletRequest.getRequestURI())
                .isSuccessful(true)
                .build();

        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    @GetMapping("/users")
    public ResponseEntity<ApiResponse> fetchStudents(HttpServletRequest httpServletRequest){
        ApiResponse  response = ApiResponse.builder()
                .data(userService.fetchAllUser())
                .status(HttpStatus.OK.value())
                .path(httpServletRequest.getRequestURI())
                .isSuccessful(true)
                .build();
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/currentUser")
    public String getCurrenuser(@AuthenticationPrincipal AppUser userDetails){
      return userDetails.getUsername();
    }
}
