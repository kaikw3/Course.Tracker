package ui;

import model.Course;
import model.CoursesTaken;

import java.sql.SQLOutput;
import java.util.Scanner;

public class CourseListApp {
    private Course course;
    private CoursesTaken coursestaken;
    private Scanner input;
    private Course removeCourse;


    // TODO
    public CourseListApp() {
        runCourseList();
    }


    private void runCourseList() {
        boolean appRun = true;
        String action = null;

        initialize();

        while (appRun) {
            displayCourseListMenu();
            action = input.next();
            action = action.toLowerCase();

            if (action.equals("quit")) {
                appRun = false;
            } else {
                processAction(action);
            }
        }

    }

    public void processAction(String action) {
        if (action.equals("add")) {
            doAddCourse();
        } else if (action.equals("remove")) {
            doRemoveCourse();
        } else if (action.equals("view")) {
            doViewCourseList();
        } else {
            System.out.println("Sorry, please input a valid selection.");
        }
        System.out.println("\t");
    }

    private void initialize() {
        course = new Course("", "", 0);
        coursestaken = new CoursesTaken();

        input = new Scanner(System.in).useDelimiter("\n");

    }

    // EFFECTS: Displays courses taken list to user
    private void displayCourseListMenu() {
        System.out.println("\t \"add\" -> Add a course");
        System.out.println("\t \"remove\" -> Remove a course");
        System.out.println("\t \"view\" -> View your course list");
        System.out.println("\t \"quit\" -> Quit Application");
        System.out.println("\nWelcome to CourseTracker, your personal course tracking application! "
                + "Please select a menu option from above by typing in the word of the action: ");

    }

    private void doAddCourse() {
        System.out.println("Please type the Course Code of the Course you would lie to add (ie. CPSC 103): ");
        String courseCode = input.next();
        System.out.println("Please type the name of the Course (ie. Introduction to Systematic Program Design): ");
        String courseName = input.next();
        System.out.println("Enter the grade you got in the course. "
                + "If you haven't received a grade yet, put 0 as a placeholder: ");
        Double courseGrade = input.nextDouble();
        Course course = new Course(courseCode, courseName, courseGrade);
        coursestaken.addCourse(course);

        System.out.println("\t");
        System.out.println(course.getCourseCode() + ": " + course.getCourseName() + " has been added to your list!");

    }

    private void doRemoveCourse() {

        if (coursestaken.getList().isEmpty()) {
            System.out.println("Sorry, you have not added any courses.");
        } else {
            System.out.println("Please type the Course Code of the Course you would lie to remove (ie. CPSC 103): ");
            String courseInput = input.next();
            for (Course c : coursestaken.getList()) {
                if (courseInput.toLowerCase().equals(c.getCourseCode().toLowerCase())) {

                    System.out.println(c.getCourseCode()
                            + ": " + c.getCourseName() + " has been removed!");

                    removeCourse = c;
                }
            }
            coursestaken.removeCourse(removeCourse);

        }
        System.out.println("\t");
    }

    private void doViewCourseList() {
        System.out.println("\t");
        if (coursestaken.getList().isEmpty()) {
            System.out.println("Sorry, you Courses Taken list is empty.");
        } else {
            for (Course c : coursestaken.getList()) {
                System.out.println("•" +  c.getCourseCode() + " | "
                        + c.getCourseName() + " | " + c.getCourseGrade());
            }
        }
        System.out.println("\t");
    }

}