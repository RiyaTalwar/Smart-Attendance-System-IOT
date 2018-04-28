package com.example.skeny.smartattendanceapp;

public class Data {
    private String CourseID, attendance;

    public Data(String courseID, String attendance){
        this.setCourseID(courseID);
        this.setAttendance(attendance);
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
}
