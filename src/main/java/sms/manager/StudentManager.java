package sms.manager;

import sms.model.Student;
import java.util.ArrayList;

public interface StudentManager {

    // Adds a new student to the database
    boolean addStudent(Student student);

    // Removes student by ID
    boolean removeStudent(String studentID);

    // Updates existing student data
    boolean updateStudent(String studentID, Student updatedStudent);

    // Returns list of all students
    ArrayList<Student> displayAllStudents();

    // Calculates average grade of all students
    double calculateAverageGrade();
}
