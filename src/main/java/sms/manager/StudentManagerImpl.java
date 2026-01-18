package sms.manager;

import sms.model.Student;
import java.sql.*;
import java.util.ArrayList;

public class StudentManagerImpl implements StudentManager {

    // SQLite database file
    private static final String DB_URL = "jdbc:sqlite:students.db";

    // Constructor - creates table if not exist
    public StudentManagerImpl() {
        createTableIfNotExists();
    }

    // Creates connection to SQLite database
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    // Creates students table
    private void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS students (" +
                "studentID TEXT PRIMARY KEY," +
                "name TEXT," +
                "age INTEGER," +
                "grade REAL" +
                ")";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);

        } catch (SQLException e) {
            System.out.println("Table error: " + e.getMessage());
        }
    }

    // Adds new student
    @Override
    public boolean addStudent(Student student) {
        String sql = "INSERT INTO students(studentID, name, age, grade) VALUES(?,?,?,?)";

        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, student.getStudentID());
            ps.setString(2, student.getName());
            ps.setInt(3, student.getAge());
            ps.setDouble(4, student.getGrade());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            return false; // duplicate ID or other SQL error
        }
    }

    // Removes student by ID
    @Override
    public boolean removeStudent(String studentID) {
        String sql = "DELETE FROM students WHERE studentID = ?";

        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, studentID);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            return false;
        }
    }

    // Updates existing student
    @Override
    public boolean updateStudent(String studentID, Student updatedStudent) {
        String sql = "UPDATE students SET name=?, age=?, grade=? WHERE studentID=?";

        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, updatedStudent.getName());
            ps.setInt(2, updatedStudent.getAge());
            ps.setDouble(3, updatedStudent.getGrade());
            ps.setString(4, studentID);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            return false;
        }
    }

    // Display list of all students
    @Override
    public ArrayList<Student> displayAllStudents() {
        ArrayList<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Student(
                        rs.getString("studentID"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getDouble("grade")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Display error");
        }

        return list;
    }

    // Calculates average grade from database
    @Override
    public double calculateAverageGrade() {
        String sql = "SELECT AVG(grade) AS avg FROM students";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            return rs.getDouble("avg");

        } catch (SQLException e) {
            return 0;
        }
    }
}