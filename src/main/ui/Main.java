package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new CourseListApp();
        } catch (FileNotFoundException e) {
            System.out.println("Application Error: file not found.");
        }
    }
}
