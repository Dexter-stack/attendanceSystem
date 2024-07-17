package com.dexter.attendanceSystem.utils;

import java.util.function.Supplier;

public class Errors {
    public static final String USER_DOES_NOT_EXIST = "This user does not exist" ;
    public static final String INVALID_PASSWORD = "invalid password";
    public static final String COURSE_ALREADY_EXIST = "This course already exist";
    public static final String COURSE_DOES_NOT_EXIST = "This course does not exist";
    public static final String UNAUTHORIZED = "Not authorized to perform this action" ;

    private Errors(){

    }

    public static final String DUPLICATE_USER  = "User Id already in use";
    public static  final String ERROR_OCCUR_WHILE_PERFORMING = "error occurred while performing this action";
}
