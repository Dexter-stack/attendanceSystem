package com.dexter.attendanceSystem.model.Response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AttendancesResponse {


    private List<AttendanceResponse> attendanceResponses;
    private Long total_size;

}
