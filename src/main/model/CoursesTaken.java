package model;

import java.util.ArrayList;

// Represents a list of courses that have been taken
public class CoursesTaken {
    ArrayList<Course> coursesTaken;


    // EFFECTS: creates a new list of courses taken
    public CoursesTaken() {
        coursesTaken = new ArrayList<>();
    }


    public ArrayList<Course> getList() {
        return this.coursesTaken;
    }

    // MODIFIES: this
    // EFFECTS: removes course from course taken list if contained in the list
    public Boolean removeCourse(Course course) {
        if (coursesTaken.contains(course)) {
            coursesTaken.remove(course);
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a course to the course taken list
    public void addCourse(Course course) {
        coursesTaken.add(course);
    }

}
