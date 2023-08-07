package ui;

import javax.swing.*;
import java.awt.*;

public class AddCoursePopup {
    private JFrame popupFrame;
    private JPanel addPanel;
    private JTextField courseCode;
    private JTextField courseGrade;
    private JTextField courseName;


    public AddCoursePopup() {
        initializeFrameAndPanel();
        initializeLabelsAndFields();
        addPanel.add(courseGrade);

    }

    private void initializeFrameAndPanel() {
        popupFrame = new JFrame("Add Course");

        addPanel = new JPanel();
        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));
        addPanel.setPreferredSize(new Dimension(200, 145));

        courseCode = new JTextField();
        courseGrade = new JTextField();
        courseName = new JTextField();

    }

    private void initializeLabelsAndFields() {
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

    public String getCourseCode() {
        return courseCode.getText();
    }

    public String getCourseName() {
        return courseName.getText();
    }

    public String getCourseGrade() {
        return (courseGrade.getText());
    }

}
