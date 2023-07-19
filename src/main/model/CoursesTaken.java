package model;

import java.util.ArrayList;

import model.Course;


public class CoursesTaken {
    ArrayList<Course> coursesTaken;


    public CoursesTaken() {
        coursesTaken = new ArrayList<Course>();
    }

    public ArrayList<Course> getList() {
        return this.coursesTaken;
    }

    public Boolean removeCourse(Course course) {
        if (coursesTaken.contains(course)) {
            coursesTaken.remove(course);
            return true;
        } else {
            return false;
        }
    }

    public void addCourse(Course course) {
        coursesTaken.add(course);
    }

}
