package com.aulia.idn.attendanceappcrud.remote;

public class APIUtils {

    private APIUtils(){

    }

    public static final String API_URL = "http://192.168.1.7/attendance/index.php/";

    public static AttendanceService getAttendanceService(){
        return RetrofitClient.getClient(API_URL).create(AttendanceService.class);
    }
}
