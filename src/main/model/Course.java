package model;

import java.util.ArrayList;
import model.CoursesTaken;

// Represents a course having a course code, course name, and course grade (in percent)
public class Course {
    private String courseCode;
    private String courseName;
    private double courseGrade;


    // TODO: Add Documentation
    // Coursecode and coursename must be in lowercase
    public Course(String courseCode, String courseName, double courseGrade) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseGrade = courseGrade;
    }

    public void setCourseGrade(double courseGrade) {
        this.courseGrade = courseGrade;
    }

    public double getCourseGrade() {
        return this.courseGrade;
    }

    public String getCourseCode() {
        return this.courseCode;
    }

    public String getCourseName() {
        return this.courseName;
    }




}
