package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CoursesTakenTest {

    private CoursesTaken testCourseList;
    private Course testCourse1;
    private Course testCourse2;

    @BeforeEach
    void runBefore() {
        testCourseList = new CoursesTaken();
        testCourse1 = new Course("cpsc 110", "dr racket", 80.2);
        testCourse2 = new Course("comm 293", "intro to accounting", 95);

    }

    @Test
    void testContructor() {
        assertEquals(0, testCourseList.coursesTaken.size());

    }

    @Test
    void testGetList() {
        ArrayList<Course> emptyCourseList = testCourseList.getList();
        assertEquals(0, emptyCourseList.size());
    }

    @Test
    void testAddCourse() {
        testCourseList.addCourse(testCourse1);
        assertEquals(testCourse1, testCourseList.coursesTaken.get(0));
        assertEquals(1, testCourseList.coursesTaken.size());
    }

    @Test
    void testRemoveCourse() {
        testCourseList.addCourse(testCourse1);
        testCourseList.addCourse(testCourse2);
        assertEquals(2, testCourseList.coursesTaken.size());

        assertTrue(testCourseList.coursesTaken.contains(testCourse2));
        assertTrue(testCourseList.coursesTaken.contains(testCourse1));

        testCourseList.removeCourse(testCourse2);
        assertEquals(1, testCourseList.coursesTaken.size());
        assertTrue(testCourseList.coursesTaken.contains(testCourse1));
        assertFalse(testCourseList.coursesTaken.contains(testCourse2));

        testCourseList.removeCourse(testCourse1);
        assertEquals(0, testCourseList.coursesTaken.size());
        assertFalse(testCourseList.coursesTaken.contains(testCourse1));
        assertFalse(testCourseList.coursesTaken.contains(testCourse2));

    }
}