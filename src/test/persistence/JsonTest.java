package persistence;

import model.Course;



import static org.junit.jupiter.api.Assertions.assertEquals;


public class JsonTest {
    protected void checkCourse(String courseCode, String courseName, double courseGrade, Course course) {
        assertEquals(courseCode, course.getCourseCode());
        assertEquals(courseName, course.getCourseName());
        assertEquals(courseGrade, course.getCourseGrade());
    }
}
