package persistence;

import model.Course;
import model.CoursesTaken;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testFileDoesNotExist() {
        JsonReader testReader = new JsonReader("./data/nonExistentFile.json");
        try {
            CoursesTaken testList = testReader.reader();
            fail("IOException is expected");
        } catch (IOException e) {
            // Exception caught
        }
    }


    @Test
    void testEmptyCourseList() {
        JsonReader testReader = new JsonReader("./data/testEmptyCourseList.json");
        try {
            CoursesTaken testList = testReader.reader();
            assertEquals("Test Course List", testList.getName());
            assertEquals(0, testList.getList().size());
        } catch (IOException e) {
            fail("File could not be read");
        }
    }

    @Test
    void testReaderGeneralCourseList() {
        JsonReader reader = new JsonReader("./data/testGeneralCourseList.json");
        try {
            CoursesTaken cr = reader.reader();
            assertEquals("Test Course List", cr.getName());
            List<Course> courses = cr.getList();
            assertEquals(2, courses.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }



}
