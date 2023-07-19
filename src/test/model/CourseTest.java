package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {
    private Course testEmptyCourse;
    private Course testCourse1;


    @BeforeEach
    void runbefore() {
        testEmptyCourse = new Course("", "", 0);
        testCourse1 = new Course("comm 293", "intro to accounting", 95.0);

    }

    @Test
    void testConstructor() {
        assertEquals(0, testEmptyCourse.getCourseGrade());
        assertEquals("", testEmptyCourse.getCourseName());
        assertEquals("", testEmptyCourse.getCourseCode());

        assertEquals(95, testCourse1.getCourseGrade());
        assertEquals("intro to accounting", testCourse1.getCourseName());
        assertEquals("comm 293", testCourse1.getCourseCode());

    }

    @Test
    void testSetCourseGrade() {
        testEmptyCourse.setCourseGrade(80);
        assertEquals(80, testEmptyCourse.getCourseGrade());

        testCourse1.setCourseGrade(20);
        assertEquals(20, testCourse1.getCourseGrade());

    }

}