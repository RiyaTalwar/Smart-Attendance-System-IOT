package com.example.skeny.smartattendanceapp;

public class DataF {
    private String CourseID, attendance, date;

    public DataF(String courseID, String attendance,String date){
        this.setCourseID(courseID);
        this.setAttendance(attendance);
        this.setdate(date);
    }
    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public void setCourseID(String courseID) {
        CourseID = courseID;
    }

    public String getAttendance() {
        return attendance;
    }

    public String getCourseID() {
        return CourseID;
    }

    public String getDate() {
        return date;
    }

    public void setdate(String date) {
        this.date = date;
    }
}
