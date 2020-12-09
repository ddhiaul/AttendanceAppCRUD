package com.aulia.idn.attendanceappcrud.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentItem {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("class")
    @Expose
    private String classs;

    @SerializedName("subject")
    @Expose
    private String subject;

    @SerializedName("attendance")
    @Expose
    private String attendance;

    public StudentItem(int id, String date, String name, String classs, String subject, String attendance){
        this.id = id;
        this.date = date;
        this.name = name;
        this.classs = classs;
        this.subject = subject;
        this.attendance = attendance;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getDate(){
        return date;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setClasss(String classs){
        this.classs = classs;
    }

    public String getClasss(){
        return classs;
    }

    public void setSubject(String subject){
        this.subject = subject;
    }

    public String getSubject(){
        return subject;
    }

    public void setAttendance(String attendance){
        this.attendance = attendance;
    }

    public String getAttendance(){ return attendance; }
}
