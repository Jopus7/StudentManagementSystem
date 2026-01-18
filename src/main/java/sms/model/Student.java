package sms.model;

public class Student {

    // Student attributes
    private String studentID;
    private String name;
    private int age;
    private double grade;

    // Constructor - init
    public Student(String studentID, String name, int age, double grade) {
        this.studentID = studentID;
        this.name = name;
        setAge(age);
        setGrade(grade);
    }

    // Getter and setter for student ID
    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    // Getter and setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and setter for age with validation age <=0
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Age must be positive.");
        }
        this.age = age;
    }

    // Getter and setter for grade with validation grade < 0 || grade > 100
    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        if (grade < 0 || grade > 100) {
            throw new IllegalArgumentException("Grade must be between 0 and 100.");
        }
        this.grade = grade;
    }

    // Displays student information
    public void displayInfo() {
        System.out.println(this);
    }

    // Returns student data as string
    @Override
    public String toString() {
        return "ID: " + studentID +
                ", Name: " + name +
                ", Age: " + age +
                ", Grade: " + grade;
    }
}