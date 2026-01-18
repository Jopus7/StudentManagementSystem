package sms.manager;

import sms.model.Student;
import java.util.ArrayList;

public interface StudentManager {

    void addStudent(Student student);

    void removeStudent(String studentID);

    void updateStudent(String studentID, Student updatedStudent);

    ArrayList<Student> displayAllStudents();

    double calculateAverageGrade();
}
