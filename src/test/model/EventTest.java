package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Citation: CPSC 210 Team

public class EventTest {


    private Event event;
    private Date date;


    //NOTE: these tests might fail if time at which line (2) below is executed
    //is different from time that line (1) is executed.  Lines (1) and (2) must
    //run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        event = new Event("Added Course, CPSC 210: Software Construction");
        date = Calendar.getInstance().getTime();
    }


    @Test
    public void testEvent() {
        assertEquals("Added Course, CPSC 210: Software Construction", event.getDescription());
        assertEquals(date, event.getDate());
    }

    @Test
    public void testToString() {
        assertEquals(date.toString() + "\n" + "Added Course, CPSC 210: Software Construction", event.toString());
    }
}
