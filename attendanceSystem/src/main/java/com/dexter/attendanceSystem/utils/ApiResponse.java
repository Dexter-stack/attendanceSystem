package com.dexter.attendanceSystem.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ApiResponse {

    private boolean isSuccessful;
    private Object data;
    private int status;
    private String path;
}
