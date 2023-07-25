package persistence;

/* Citation

Title: JSONSerializationDemo Source Code
Author: CPSC 210 Team
Date: July 2023
Availability: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

 */


import model.CoursesTaken;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represents a writer that writes JSON representation of CoursesTaken to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs a writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file
    //          cannot be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of Courses Taken to file
    public void write(CoursesTaken cr) {
        JSONObject json = cr.toJson();
        save(json.toString(TAB));

    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    public void save(String json) {
        writer.print(json);
    }

}
