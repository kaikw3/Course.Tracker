package ui;

import javax.swing.*;
import java.awt.*;

// Represents a popup window to add a course
public class AddCoursePopup {
    private JFrame popupFrame;
    private JPanel addPanel;
    private JTextField courseCode;
    private JTextField courseGrade;
    private JTextField courseName;


    // EFFECTS: creates a new popup window to add a course
    public AddCoursePopup() {
        initializeFrameAndPanel();
        initializeLabelsAndFields();
        addPanel.add(courseGrade);

    }

    // EFFECTS: initializes the frame and panel of the popup
    private void initializeFrameAndPanel() {
        popupFrame = new JFrame("Add Course");

        addPanel = new JPanel();
        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));
        addPanel.setPreferredSize(new Dimension(200, 145));
    }

    // EFFECTS: initializes the labels and text fields for the popup
    private void initializeLabelsAndFields() {
        courseCode = new JTextField();
        courseGrade = new JTextField();
        courseName = new JTextField();

        addPanel.add(new JLabel("Course Code: "));
        addPanel.add(courseCode);
        addPanel.add(Box.createHorizontalStrut(15));
        addPanel.add(new JLabel("Course Name: "));
        addPanel.add(courseName);
        addPanel.add(Box.createHorizontalStrut(15));
        addPanel.add(new JLabel("Course Grade: "));
    }

    public JPanel getAddPanel() {
        return addPanel;
    }

    // EFFECTS: returns the course code text field as a string
    public String getCourseCode() {
        return courseCode.getText();
    }

    // EFFECTS: returns the course name text field as a string
    public String getCourseName() {
        return courseName.getText();
    }

    // EFFECTS: returns the course grade text field as a string
    public String getCourseGrade() {
        return (courseGrade.getText());
    }

}
