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


// Represents a graphical user interface
public class CourseListGUI extends JFrame {

    protected static final String JSON_STORAGE = "./data/coursesTaken.json";
    private static final int WIDTH = 600;
    private static final int HEIGHT = 700;

    private CoursesTaken coursesTaken;
    private DefaultTableModel courseTable;

    private JFrame mainScreen;
    private JPanel titleBar;
    private JPanel bottomBar;
    private JTable courseList;

    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private ImageIcon ubcLogo;

    private Border border;
    private Color navyBlue;
    private Color yellow;

    private String courseCode;
    private String courseName;
    private String courseGrade;

    // EFFECTS: Creates the GUI and initializes its components
    public CourseListGUI() {
        super("CourseListGUI");
        initializeData();
        initializeScreen();
        mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // MODIFIES: this
    // EFFECTS: Creates and initializes the main screen and its components
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

    // MODIFIES: this
    // EFFECTS: Creates and initializes the title bar of the screen
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

    // MODIFIES: this
    // EFFECTS: Creates and initializes the bottom bar of the screen
    private void initializeBottomBar() {
        bottomBar = new JPanel();
        bottomBar.setPreferredSize(new Dimension(400, 60));
        bottomBar.setBackground(navyBlue);
        border = BorderFactory.createEmptyBorder();

        constructButtons();
        mainScreen.add(bottomBar, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: Creates and initializes buttons
    private void constructButtons() {
        constructAddButton();
        bottomBar.add(Box.createHorizontalStrut(10));
        constructRemoveButton();
        bottomBar.add(Box.createHorizontalStrut(10));
        constructSaveButton();
        bottomBar.add(Box.createHorizontalStrut(10));
        constructLoadButton();
    }

    // MODIFIES: this
    // EFFECTS: Creates a new add button
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

    // MODIFIES: this
    // EFFECTS: Creates a new remove button
    private void constructRemoveButton() {
        JButton removeButton = new JButton("Remove Course");
        removeButton.setVerticalAlignment(JButton.BOTTOM);
        removeButton.setFont(new Font("Calibri", Font.BOLD, 15));
        removeButton.setBorder(border);
        removeButton.setBackground(yellow);
        removeButton.addActionListener(e -> removeCourse());
        bottomBar.add(removeButton);
    }

    // MODIFIES: this
    // EFFECTS: Creates a new load button
    private void constructLoadButton() {
        JButton loadButton = new JButton("Load Course List");
        loadButton.setVerticalAlignment(JButton.BOTTOM);
        loadButton.setFont(new Font("Calibri", Font.BOLD, 15));
        loadButton.setBorder(border);
        loadButton.setBackground(yellow);
        loadButton.addActionListener(e -> loadCourse());
        bottomBar.add(loadButton);
    }

    // MODIFIES: this
    // EFFECTS: Creates a new save button
    private void constructSaveButton() {
        JButton saveButton = new JButton("Save Course List");
        saveButton.setVerticalAlignment(JButton.BOTTOM);
        saveButton.setFont(new Font("Calibri", Font.BOLD, 15));
        saveButton.setBorder(border);
        saveButton.setBackground(yellow);
        saveButton.addActionListener(e -> saveCourse());
        bottomBar.add(saveButton);

    }

    // MODIFIES: this
    // EFFECTS: Initializes the table model and table to display course list
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

    // MODIFIES: this
    // EFFECTS: Initializes the data of the application
    private void initializeData() {
        jsonReader = new JsonReader(JSON_STORAGE);
        jsonWriter = new JsonWriter(JSON_STORAGE);
        initializeImage();

        coursesTaken = new CoursesTaken("Course List");

    }

    // EFFECTS: Initializes the image and its sizing
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

    // MODIFIES: this, coursesTaken
    // EFFECTS: Creates a popup window to get course details input from the user and add if not already added,
    //          adds the course to the table and coursesTaken list, but if already added, shows confirmation dialog
    //          that the course has already been added
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

    // MODIFIES: this, coursesTaken
    // EFFECTS: Creates a popup window to get course code of course to remove from the user and add if contained in
    //          course list, removes it from the table and coursesTaken list
    private void removeCourse() {
        String removeCourseName = JOptionPane.showInputDialog(
                "Please enter the course code of the course you would like to remove.");
        String removeCourseTable = "";
        for (int i = 0; i < courseTable.getRowCount(); i++) {
            removeCourseTable = courseTable.getValueAt(i, 0).toString();
            if (removeCourseTable.equalsIgnoreCase(removeCourseName)) {
                courseTable.removeRow(i);
            }
        }
        Course removeCourseList = null;
        for (Course c : coursesTaken.getList()) {
            if (c.getCourseCode().equalsIgnoreCase(removeCourseName)) {
                removeCourseList = c;
            }
        }
        if (coursesTaken.removeCourse(removeCourseList)) {
            coursesTaken.removeCourse(removeCourseList);
        } else {
            JOptionPane.showMessageDialog(null, "Sorry,"
                    + " this course is not in your course list!");
        }
    }

    // MODIFIES: this, coursesTaken
    // EFFECTS: Creates a confirmation save dialog, and if confirmed, then saves the current course list to
    //          destination file, once completed will show an action completed dialog
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

    // MODIFIES: this, coursesTaken
    // EFFECTS: Creates a confirmation save dialog, and if confirmed, then loads data stored in file and
    //          loads it to the table and coursesTaken list, once completed will show an action completed dialog
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
                    if (coursesTaken.addCourse(c)) {
                        courseTable.addRow(loadCourse);
                        coursesTaken.addCourse(c);
                    }
                }
                JOptionPane.showMessageDialog(null, "Load Completed!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error: Unable to load file!");
            }
        }

    }
}



