package sms.model;

public class Student {

    private String studentID;
    private String name;
    private int age;
    private double grade;

    public Student(String studentID, String name, int age, double grade) {
        this.studentID = studentID;
        this.name = name;
        setAge(age);
        setGrade(grade);
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Age must be positive.");
        }
        this.age = age;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        if (grade < 0 || grade > 100) {
            throw new IllegalArgumentException("Grade must be between 0 and 100.");
        }
        this.grade = grade;
    }

    public void displayInfo() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "ID: " + studentID +
                ", Name: " + name +
                ", Age: " + age +
                ", Grade: " + grade;
    }
}
