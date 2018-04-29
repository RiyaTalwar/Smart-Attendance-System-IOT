package com.example.abhin.iot;

public class STable {

    private String course_id;
    private String course_name;
    private String attendance;
    private String lectures;

    public STable(String course_id, String course_name, String attendance, String lectures) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.attendance = attendance;
        this.lectures = lectures;
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

    public String getLectures() {
        return lectures;
    }

    public void setLectures(String lectures) {
        this.lectures = lectures;
    }
}
