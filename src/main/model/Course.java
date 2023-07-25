package model;


import org.json.JSONObject;
import persistence.Writeable;

// Represents a course having a course code, course name, and course grade (in percent)
public class Course implements Writeable {
    private String courseCode;      // Course code
    private String courseName;      // Name of the course
    private double courseGrade;     // the grade received in the course


    // EFFECTS: creates a course with course code, course name, and course grade
    public Course(String courseCode, String courseName, double courseGrade) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseGrade = courseGrade;
    }

    // MODIFIES: this
    // EFFECTS: sets course grade to the double inputted
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


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Course Code", courseCode);
        json.put("Course Name", courseName);
        json.put("Course Grade", courseGrade);

        return json;
    }

}
