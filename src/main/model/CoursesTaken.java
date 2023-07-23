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
    // EFFECTS: adds a course to the course taken list and returns
    //          true, false if course is already in list
    public boolean addCourse(Course course) {
        boolean added = false;
        for (Course c : this.coursesTaken) {
            if (c.getCourseCode().toLowerCase().equals(course.getCourseCode().toLowerCase())) {
                added = true;
                break;
            }
        }
        if (!added || coursesTaken.isEmpty()) {
            this.coursesTaken.add(course);
            return true;
        } else {
            return false;
        }

    }

}