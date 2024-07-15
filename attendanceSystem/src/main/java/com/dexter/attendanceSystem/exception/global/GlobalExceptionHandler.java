package com.dexter.attendanceSystem.exception.global;

import com.dexter.attendanceSystem.exception.StudentException;
import com.dexter.attendanceSystem.model.Response.MessageResponse;
import com.dexter.attendanceSystem.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        List<String> errorMessages = new ArrayList<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMessages.add(fieldError.getDefaultMessage());
        }
        System.out.println("Validation errors: " + errorMessages);
        ApiResponse errorResponse = ApiResponse.builder()
                .isSuccessful(false)
                .path(request.getContextPath())
                .status(HttpStatus.BAD_REQUEST.value())
                .data(StringUtils.collectionToCommaDelimitedString(errorMessages))
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }



   @ResponseStatus(HttpStatus.BAD_REQUEST)
   @ResponseBody
   @ExceptionHandler(StudentException.class)
    public ResponseEntity<ApiResponse> handleStudentException(StudentException exception,
                                                              WebRequest request){
        MessageResponse messageResponse = MessageResponse.builder().message(exception.getMessage()).build();
        ApiResponse response = ApiResponse.builder()
                .isSuccessful(false)
                .data(messageResponse)
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getContextPath())
                .build();
        return  new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
