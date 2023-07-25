package persistence;


import model.Course;
import model.CoursesTaken;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{


    @Test
    void testInvalidFile() {
        try {
            CoursesTaken cr = new CoursesTaken("Test Course List");
            JsonWriter testWriter = new JsonWriter("./data/my\0illegal:fileName.json");
            testWriter.open();
            fail("IOException was not caught");
        } catch (IOException e) {
            // Exception was caught
        }
    }

    @Test
    void testEmptyCourseList() {
        try {
            CoursesTaken cr = new CoursesTaken("Test Course List");
            JsonWriter writer = new JsonWriter("./data/testEmptyCourseList.json");
            writer.open();
            writer.write(cr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testEmptyCourseList.json");
            cr = reader.reader();
            assertEquals("Test Course List", cr.getName());
            assertEquals(0, cr.getList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testGeneralCourseList() {
        try {
            CoursesTaken cr = new CoursesTaken("Test Course List");
            cr.addCourse(new Course("CPSC 210", "Software Construction", 90));
            cr.addCourse(new Course("COMM 293", "Financial Accounting", 96));
            JsonWriter writer = new JsonWriter("./data/testGeneralCourseList.json");
            writer.open();
            writer.write(cr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testGeneralCourseList.json");
            cr = reader.reader();
            assertEquals("Test Course List", cr.getName());
            List<Course> course = cr.getList();
            assertEquals(2, course.size());
            checkCourse("CPSC 210", "Software Construction", 90, course.get(0));
            checkCourse("COMM 293", "Financial Accounting", 96, course.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

