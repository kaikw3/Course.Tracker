package ui;

import model.Course;
import model.CoursesTaken;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


import static java.lang.Double.parseDouble;

public class CourseListGUI extends JFrame {

    protected CoursesTaken coursesTaken;
    private DefaultTableModel courseTable;

    protected static final String JSON_STORAGE = "./data/coursesTaken.json";
    private static final int WIDTH = 600;
    private static final int HEIGHT = 700;

    private JFrame mainScreen;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private ImageIcon ubcLogo;

    private JPanel titleBar;
    private JPanel bottomBar;
    protected JTable courseList;

    private Border border;
    private Color navyBlue;
    private Color yellow;


    private String courseCode;
    private String courseName;
    private String courseGrade;


    public CourseListGUI() {
        super("CourseListGUI");
        initializeData();
        initializeScreen();
        mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initializeScreen() {
        mainScreen = new JFrame("Course List");
        navyBlue = new Color(0, 0, 153);
        yellow = new Color(255, 255, 153);
        initializeTitleBar();
        initializeBottomBar();
        initializeList();

        mainScreen.setSize(WIDTH, HEIGHT);
        mainScreen.setLocationRelativeTo(null);
        mainScreen.setVisible(true);

    }

    private void initializeTitleBar() {
        titleBar = new JPanel();
        titleBar.setPreferredSize(new Dimension(400, 80));
        titleBar.setBackground(navyBlue);
        JLabel textTitle = new JLabel("Course Tracker");
        textTitle.setPreferredSize(new Dimension(600, 70));
        textTitle.setForeground(Color.white);
        textTitle.setFont(new Font("Calibri", Font.BOLD, 20));
        textTitle.setHorizontalAlignment(JLabel.CENTER);
        titleBar.add(textTitle);
        mainScreen.add(titleBar, BorderLayout.NORTH);
    }

    private void initializeBottomBar() {
        bottomBar = new JPanel();
        bottomBar.setPreferredSize(new Dimension(400, 60));
        bottomBar.setBackground(navyBlue);
        border = BorderFactory.createEmptyBorder();

        constructButtons();

        mainScreen.add(bottomBar, BorderLayout.SOUTH);
    }

    private void constructButtons() {
        constructAddButton();
        bottomBar.add(Box.createHorizontalStrut(10));
        constructRemoveButton();
        bottomBar.add(Box.createHorizontalStrut(10));
        constructSaveButton();
        bottomBar.add(Box.createHorizontalStrut(10));
        constructLoadButton();
    }

    private void constructAddButton() {
        JButton addButton = new JButton("Add Course");
        addButton.setVerticalAlignment(JButton.BOTTOM);
        addButton.setFont(new Font("Calibri", Font.BOLD, 15));
        addButton.setBorder(border);
        addButton.setBackground(yellow);

        addButton.setActionCommand("Add");
        addButton.addActionListener(e -> addCourse());
        bottomBar.add(addButton);
    }

    private void constructRemoveButton() {
        JButton removeButton = new JButton("Remove Course");
        removeButton.setVerticalAlignment(JButton.BOTTOM);
        removeButton.setFont(new Font("Calibri", Font.BOLD, 15));
        removeButton.setBorder(border);
        removeButton.setBackground(yellow);
        removeButton.addActionListener(e -> removeCourse());
        bottomBar.add(removeButton);
    }

    private void constructLoadButton() {
        JButton loadButton = new JButton("Load Course List");
        loadButton.setVerticalAlignment(JButton.BOTTOM);
        loadButton.setFont(new Font("Calibri", Font.BOLD, 15));
        loadButton.setBorder(border);
        loadButton.setBackground(yellow);
        loadButton.addActionListener(e -> loadCourse());
        bottomBar.add(loadButton);
    }

    private void constructSaveButton() {
        JButton saveButton = new JButton("Save Course List");
        saveButton.setVerticalAlignment(JButton.BOTTOM);
        saveButton.setFont(new Font("Calibri", Font.BOLD, 15));
        saveButton.setBorder(border);
        saveButton.setBackground(yellow);
        saveButton.addActionListener(e -> saveCourse());
        bottomBar.add(saveButton);

    }

    private void initializeList() {
        courseTable = new DefaultTableModel();
        courseList = new JTable(courseTable);
        courseTable.addColumn("Course Code");
        courseTable.addColumn("Course Name");
        courseTable.addColumn("Course Grade");


        courseList.setBounds(30, 40, WIDTH, HEIGHT - 100);
        JScrollPane scrollPane = new JScrollPane(courseList);

        mainScreen.add(scrollPane, BorderLayout.CENTER);
    }


    private void initializeData() {
        jsonReader = new JsonReader(JSON_STORAGE);
        jsonWriter = new JsonWriter(JSON_STORAGE);
        initializeImage();

        coursesTaken = new CoursesTaken("Course List");

    }

    private void initializeImage() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("./data/UBC_logo.png"));
        } catch (IOException e) {
            System.out.println("Image failed to initialize");
        }

        Image resized = image.getScaledInstance(40, 55, Image.SCALE_SMOOTH);
        ubcLogo = new ImageIcon(resized);
    }

    private void addCourse() {
        AddCoursePopup addCoursePopup = new AddCoursePopup();
        JPanel addPanel = addCoursePopup.getAddPanel();

        int result = JOptionPane.showConfirmDialog(null, addPanel,
                "Please enter the Course Details", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, ubcLogo);
        if (result == JOptionPane.OK_OPTION) {
            courseCode = addCoursePopup.getCourseCode();
            courseName = addCoursePopup.getCourseName();
            courseGrade = addCoursePopup.getCourseGrade();

            Course newCourse = new Course(courseCode, courseName, parseDouble(courseGrade));
            if (coursesTaken.addCourse(newCourse)) {
                coursesTaken.addCourse(newCourse);
                String[] newCourseData = {courseCode, courseName, courseGrade};
                courseTable.addRow(newCourseData);
            } else {
                JOptionPane.showMessageDialog(null, "Sorry this course has already been added!");
            }

        }
    }

    private void removeCourse() {
        String removeCourseName = JOptionPane.showInputDialog(
                "Please enter the course code of the course you would like to remove.");
        String removeCourse = "";
        for (int i = 0; i < courseTable.getRowCount(); i++) {
            removeCourse = courseTable.getValueAt(i, 0).toString();
            if (removeCourse.equalsIgnoreCase(removeCourseName)) {
                courseTable.removeRow(i);
            }
        }
    }

    private void saveCourse() {
        int saveConfirmation = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you would like to save this course list?");
        if (saveConfirmation == JOptionPane.YES_OPTION) {
            try {
                jsonWriter.open();
                jsonWriter.write(coursesTaken);
                jsonWriter.close();
                JOptionPane.showMessageDialog(null, "Save Complete!");
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Error: Unable to save file!");

            }
        }
    }

    private void loadCourse() {
        int loadConfirmation = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you would like to load your course list on file?");
        if (loadConfirmation == JOptionPane.YES_OPTION) {
            try {
                CoursesTaken loadedList = jsonReader.reader();
                for (Course c : loadedList.getList()) {
                    String[] loadCourse = {c.getCourseCode(),
                            c.getCourseName(), Double.toString(c.getCourseGrade())};
                    courseTable.addRow(loadCourse);
                    coursesTaken.addCourse(c);
                }
                JOptionPane.showMessageDialog(null, "Load Completed!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error: Unable to load file!");
            }
        }

    }
}



