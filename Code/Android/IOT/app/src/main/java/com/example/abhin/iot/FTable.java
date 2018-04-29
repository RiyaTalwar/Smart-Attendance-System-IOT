package com.example.abhin.iot;

public class FTable {
    private String course_id;
    private String course_name;
    private String attendance;
    private String student;
    private String date;

    public FTable(String course_id, String course_name, String attendance, String student, String date) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.attendance = attendance;
        this.student = student;
        this.date = date;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
