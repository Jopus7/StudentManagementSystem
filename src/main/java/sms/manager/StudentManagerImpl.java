package sms.manager;

import sms.model.Student;

import java.sql.*;
import java.util.ArrayList;

public class StudentManagerImpl implements StudentManager {

    private static final String DB_URL = "jdbc:sqlite:students.db";

    public StudentManagerImpl() {
        createTableIfNotExists();
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    private void createTableIfNotExists() {
        String sql = """
                CREATE TABLE IF NOT EXISTS students (
                    studentID TEXT PRIMARY KEY,
                    name TEXT,
                    age INTEGER,
                    grade REAL
                );
                """;

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    @Override
    public void addStudent(Student student) {
        String sql = "INSERT INTO students(studentID, name, age, grade) VALUES(?,?,?,?)";

        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, student.getStudentID());
            ps.setString(2, student.getName());
            ps.setInt(3, student.getAge());
            ps.setDouble(4, student.getGrade());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Add error: " + e.getMessage());
        }
    }

    @Override
    public void removeStudent(String studentID) {
        String sql = "DELETE FROM students WHERE studentID = ?";

        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, studentID);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Remove error: " + e.getMessage());
        }
    }

    @Override
    public void updateStudent(String studentID, Student updatedStudent) {
        String sql = "UPDATE students SET name=?, age=?, grade=? WHERE studentID=?";

        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, updatedStudent.getName());
            ps.setInt(2, updatedStudent.getAge());
            ps.setDouble(3, updatedStudent.getGrade());
            ps.setString(4, studentID);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Update error: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Student> displayAllStudents() {
        ArrayList<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Student s = new Student(
                        rs.getString("studentID"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getDouble("grade")
                );
                list.add(s);
            }

        } catch (SQLException e) {
            System.err.println("Display error: " + e.getMessage());
        }

        return list;
    }

    @Override
    public double calculateAverageGrade() {
        String sql = "SELECT AVG(grade) as avg FROM students";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            return rs.getDouble("avg");

        } catch (SQLException e) {
            System.err.println("Average error: " + e.getMessage());
        }

        return 0;
    }
}
