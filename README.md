# Student Management System

Java application to manage student data using SQLite and Swing
Application allows to add, remove, update and display students data and calculate the average grade.


## Features

The application provides the following functions:

-   Add new student
-   Remove student by ID
-   Update student data
-   Display all students
-   Calculate average grade

## Technologies

The project was created using:

-   Java
-   Swing (GUI)
-   JDBC
-   SQLite
-   Maven

## Project Structure

Packages:

sms.model -> Student class (data model)\
sms.manager -> business logic and database access\
sms.gui -> graphical user interface\
sms.main -> application entry point

This structure ensures clear separation between GUI, logic and data.

## Database

The application uses SQLite database.

Database file:

students.db

Table structure:

``` sql
CREATE TABLE students (
    studentID TEXT PRIMARY KEY,
    name TEXT,
    age INTEGER,
    grade REAL
);
```

The table is created automatically when the application starts if it
does not exist.

## How to Run

1.  Open the project in IntelliJ IDEA
2.  Make sure JDK and Maven are configured
3.  Run Main.java
4.  The graphical interface will appear


## Error Handling

The application handles:

-   SQL errors during database operations
-   Invalid user input
-   Logical errors such as removing or updating non-existing students


## Design Patterns Used

### 1. MVC (Model View Controller)

The project follows MVC architecture:

-   Model: Student
-   View: StudentGUI
-   Controller / Logic: StudentManagerImpl

Example:

``` java
public class Student { }
public class StudentGUI extends JFrame { }
public class StudentManagerImpl implements StudentManager { }
```

### 2. DAO (Data Access Object)

StudentManagerImpl works as DAO layer.

Example:

``` java
public boolean addStudent(Student student) {
    String sql = "INSERT INTO students VALUES(?,?,?,?)";
}
```

### 3. Observer

Swing buttons use ActionListener.

Example:

``` java
addBtn.addActionListener(e -> {
    manager.addStudent(student);
});
```

## Code Organization

The code is modular. Each layer is separated.

## Final Result

The project is a fully functional Java application with GUI and SQLite
database.


## Code Organization

The project is divided into packages.  
Each part of the application has its own role.

GUI, database logic and data model are separated, which makes the code clear.

## Final Result

The project is a working Java application with graphical interface and SQLite database.


