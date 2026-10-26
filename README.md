# Student Management System

A console-based Student Management System developed using Java, JDBC, and Oracle Database.

## Features

- Add new students
- View all student records
- Search students by ID
- Search students by name
- Update student information
- Delete student records
- Sort students by marks in descending order
- Display total student count and average marks

## Technologies Used

- Java
- JDBC
- Oracle Database 11g XE
- SQL
- VS Code

## Database Setup

Create a table named `students` in your Oracle database:

```sql
CREATE TABLE students (
    id NUMBER PRIMARY KEY,
    name VARCHAR2(100) NOT NULL,
    age NUMBER NOT NULL,
    course VARCHAR2(100) NOT NULL,
    marks NUMBER(5,2) NOT NULL
);
```

## Database Configuration

Set these environment variables in PowerShell before running the application:

```powershell
$env:DB_USERNAME = "emp_user"
$env:DB_PASSWORD = "YOUR_ORACLE_PASSWORD"
```

Replace the placeholder with your actual Oracle password. Never commit database passwords to GitHub.

## Compile and Run

```powershell
javac -cp "lib\ojdbc17.jar" -d out src\*.java
java -cp "out;lib\ojdbc17.jar" StudentManagementSystem
```

## Learning Objectives

This project demonstrates Java object-oriented programming, JDBC connectivity, SQL CRUD operations, exception handling, and database integration.
