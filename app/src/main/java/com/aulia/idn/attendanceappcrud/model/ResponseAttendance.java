package com.aulia.idn.attendanceappcrud.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseAttendance {

    @SerializedName("student")
    private List<StudentItem> student;

    @SerializedName("error")
    private boolean error;

    @SerializedName("status")
    private int status;

    public ResponseAttendance() {
    }

    public void setStudent(List<StudentItem> student){
        this.student = student;
    }

    public List<StudentItem> getStudent(){
        return student;
    }

    public void setError(boolean error){
        this.error = error;
    }

    public boolean isError(){
        return error;
    }

    public void setStatus(int status){
        this.status = status;
    }

    public int getStatus(){
        return status;
    }
}
