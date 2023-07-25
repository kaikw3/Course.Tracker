package persistence;

/* Citation

Title: JSONSerializationDemo Source Code
Author: CPSC 210 Team
Date: July 2023
Availability: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

 */


import model.Course;
import model.CoursesTaken;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


// Represents a reader that read courses taken from JSON data stored in file
public class JsonReader {
    private String sourceFile;

    // EFFECTS: constructs a reader to read from a source file
    public JsonReader(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    // EFFECTS: reads courses taken from files and returns it; throws IOException
    //          if error occurs while reading data from file
    public CoursesTaken reader() throws IOException {
        String jsonData = readFile(sourceFile);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String sourcefile) throws IOException {
        StringBuilder content = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(sourcefile), StandardCharsets.UTF_8)) {
            stream.forEach(s -> content.append(s));
        }
        return content.toString();
    }

    // EFFECTS: parses courses taken from JSON object and returns it
    private CoursesTaken parseList(JSONObject jsonObject) {
        String course = jsonObject.getString("name");
        CoursesTaken cr = new CoursesTaken(course);
        addCourses(cr, jsonObject);
        return cr;
    }

    // MODIFIES: cr
    // EFFECTS: parses courses from JSON object and adds them to courses taken
    private void addCourses(CoursesTaken cr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Course");
        for (Object json : jsonArray) {
            JSONObject nextCourse = (JSONObject) json;
            addCourse(cr, nextCourse);
        }
    }

    // MODIFIES: cr
    // EFFECTS: parses course from JSON object and adds them to courses taken
    private void addCourse(CoursesTaken cr, JSONObject jsonObject) {
        String courseCode = jsonObject.getString("courseCode");
        String courseName = jsonObject.getString("courseName");
        Double courseGrade = Double.valueOf(jsonObject.getDouble("courseGrade"));
        Course course = new Course(courseCode, courseName, courseGrade);
        cr.addCourse(course);
    }

}




