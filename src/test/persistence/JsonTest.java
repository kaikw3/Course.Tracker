package persistence;

import model.CoursesTaken;
import model.Course;



import static org.junit.jupiter.api.Assertions.assertEquals;


public class JsonTest {
    private Course course;
    private CoursesTaken cr;

    protected void checkCourse(String courseCode, String courseName, double courseGrade) {
        assertEquals(courseCode, course.getCourseCode());
        assertEquals(courseName, course.getCourseName());
        assertEquals(courseGrade, course.getCourseGrade());
    }
}
