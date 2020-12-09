package com.aulia.idn.attendanceappcrud.remote;

import com.aulia.idn.attendanceappcrud.model.StudentItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface AttendanceService {

    @GET("student/get/")
    Call<List<StudentItem>> getAttendance();

    @FormUrlEncoded
    @POST("student/add")
    Call<StudentItem> addAttendance(@Field("date") String date,
                                    @Field("name") String name,
                                    @Field("class") String classs,
                                    @Field("subject") String subject,
                                    @Field("attendance") String attendace);

    @FormUrlEncoded
    @PUT("student/update/")
    Call<StudentItem> updateAttendance(@Field("id") int id,
                                       @Field("date") String date,
                                       @Field("name") String name,
                                       @Field("class") String classs,
                                       @Field("subject") String subject,
                                       @Field("attendance") String attendace);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = ("student/delete/"), hasBody = true)
    Call<StudentItem> deleteAttendance(@Field("id") int id);
}
