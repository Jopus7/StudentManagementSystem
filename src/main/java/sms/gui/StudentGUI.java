package sms.gui;

import sms.manager.StudentManager;
import sms.manager.StudentManagerImpl;
import sms.model.Student;

import javax.swing.*;
import java.awt.*;


public class StudentGUI extends JFrame {

    // Swing components - input
    private JTextField idField, nameField, ageField, gradeField;

    // Text area - output
    private JTextArea outputArea;

    //Logic manager
    private StudentManager manager;

    //GUI constructor
    public StudentGUI() {
        manager = new StudentManagerImpl();

        // GUI settings
        setTitle("Student Management System");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input form
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));

        inputPanel.add(new JLabel("Student ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        inputPanel.add(ageField);

        inputPanel.add(new JLabel("Grade:"));
        gradeField = new JTextField();
        inputPanel.add(gradeField);

        // Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1));

        JButton addBtn = new JButton("Add Student");
        JButton removeBtn = new JButton("Remove Student");
        JButton updateBtn = new JButton("Update Student");
        JButton displayBtn = new JButton("Display All Students");
        JButton avgBtn = new JButton("Calculate Average");

        buttonPanel.add(addBtn);
        buttonPanel.add(removeBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(displayBtn);
        buttonPanel.add(avgBtn);

        // Left panel - input, buttons
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(inputPanel, BorderLayout.NORTH);
        leftPanel.add(buttonPanel, BorderLayout.CENTER);

        add(leftPanel, BorderLayout.WEST);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);


        // Add student
        addBtn.addActionListener(e -> {
            try {
                Student s = new Student(
                        idField.getText(),
                        nameField.getText(),
                        Integer.parseInt(ageField.getText()),
                        Double.parseDouble(gradeField.getText())
                );

                if(manager.addStudent(s)){
                    outputArea.setText("Student added.");
                } else {
                    outputArea.setText("Student with this ID already exists.");
                }

            } catch (Exception ex) {
                outputArea.setText("Invalid input.");
            }
        });

        // Remove student
        removeBtn.addActionListener(e -> {
            if(manager.removeStudent(idField.getText())){
                outputArea.setText("Student removed.");
            } else {
                outputArea.setText("Student not found.");
            }
        });

        // Update student
        updateBtn.addActionListener(e -> {
            try {
                Student s = new Student(
                        idField.getText(),
                        nameField.getText(),
                        Integer.parseInt(ageField.getText()),
                        Double.parseDouble(gradeField.getText())
                );

                if(manager.updateStudent(idField.getText(), s)){
                    outputArea.setText("Student updated.");
                } else {
                    outputArea.setText("Student not found.");
                }

            } catch (Exception ex) {
                outputArea.setText("Invalid input.");
            }
        });

        // Display all students
        displayBtn.addActionListener(e -> {
            outputArea.setText("");
            manager.displayAllStudents().forEach(s ->
                    outputArea.append(s.toString() + "\n")
            );
        });

        // Calculate average grade
        avgBtn.addActionListener(e -> {
            double avg = manager.calculateAverageGrade();
            outputArea.setText("Average grade: " + avg);
        });
    }
}
