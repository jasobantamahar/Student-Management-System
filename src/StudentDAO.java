import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentDAO {

    Scanner sc = new Scanner(System.in);

    // Add Student
    public void addStudent() {
        try (Connection con = DBConnection.getConnection()) {

            if (con == null) {
                System.out.println("Database connection failed!");
                return;
            }

            System.out.print("Enter Student ID: ");
            int id = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Age: ");
            int age = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Course: ");
            String course = sc.nextLine();

            System.out.print("Enter Marks (0-100): ");
            double marks = Double.parseDouble(sc.nextLine());

            if (id <= 0 || age <= 0 || marks < 0 || marks > 100
                    || name.isBlank() || course.isBlank()) {
                System.out.println("Invalid student details!");
                return;
            }

            String sql = "INSERT INTO students (id, name, age, course, marks) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.setString(2, name);
                ps.setInt(3, age);
                ps.setString(4, course);
                ps.setDouble(5, marks);

                ps.executeUpdate();
                System.out.println("Student added successfully!");
            }

        } catch (NumberFormatException e) {
            System.out.println("Please enter valid numeric values.");
        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    // View Students
    public void viewStudents() {
        String sql = "SELECT * FROM students ORDER BY id";

        try (Connection con = DBConnection.getConnection()) {

            if (con == null) {
                System.out.println("Database connection failed!");
                return;
            }

            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                System.out.printf("%-10s %-20s %-10s %-20s %-10s%n",
                        "ID", "NAME", "AGE", "COURSE", "MARKS");

                while (rs.next()) {
                    System.out.printf("%-10d %-20s %-10d %-20s %-10.2f%n",
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("age"),
                            rs.getString("course"),
                            rs.getDouble("marks"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error viewing students: " + e.getMessage());
        }
    }

    // Search Student
    public void searchStudent() {
        System.out.print("Enter Student ID to search: ");

        try {
            int id = Integer.parseInt(sc.nextLine());
            String sql = "SELECT * FROM students WHERE id = ?";

            try (Connection con = DBConnection.getConnection()) {
                if (con == null) {
                    System.out.println("Database connection failed!");
                    return;
                }

                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, id);

                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            System.out.println("ID: " + rs.getInt("id"));
                            System.out.println("Name: " + rs.getString("name"));
                            System.out.println("Age: " + rs.getInt("age"));
                            System.out.println("Course: " + rs.getString("course"));
                            System.out.println("Marks: " + rs.getDouble("marks"));
                        } else {
                            System.out.println("Student not found!");
                        }
                    }
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid student ID.");
        } catch (SQLException e) {
            System.out.println("Error searching student: " + e.getMessage());
        }
    }

    // Delete Student
    public void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");

        try {
            int id = Integer.parseInt(sc.nextLine());
            String sql = "DELETE FROM students WHERE id = ?";

            try (Connection con = DBConnection.getConnection()) {
                if (con == null) {
                    System.out.println("Database connection failed!");
                    return;
                }

                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, id);

                    int rows = ps.executeUpdate();

                    if (rows > 0) {
                        System.out.println("Student deleted successfully!");
                    } else {
                        System.out.println("Student not found!");
                    }
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid student ID.");
        } catch (SQLException e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }

    // Update Student
    public void updateStudent() {
        try {
            System.out.print("Enter Student ID to update: ");
            int id = Integer.parseInt(sc.nextLine());

            System.out.print("Enter new name: ");
            String name = sc.nextLine();

            System.out.print("Enter new age: ");
            int age = Integer.parseInt(sc.nextLine());

            System.out.print("Enter new course: ");
            String course = sc.nextLine();

            System.out.print("Enter new marks (0-100): ");
            double marks = Double.parseDouble(sc.nextLine());

            if (name.isBlank() || course.isBlank()
                    || age <= 0 || marks < 0 || marks > 100) {
                System.out.println("Invalid student details!");
                return;
            }

            String sql = "UPDATE students SET name = ?, age = ?, course = ?, marks = ? WHERE id = ?";

            try (Connection con = DBConnection.getConnection()) {
                if (con == null) {
                    System.out.println("Database connection failed!");
                    return;
                }

                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, name);
                    ps.setInt(2, age);
                    ps.setString(3, course);
                    ps.setDouble(4, marks);
                    ps.setInt(5, id);

                    int rows = ps.executeUpdate();

                    if (rows > 0) {
                        System.out.println("Student updated successfully!");
                    } else {
                        System.out.println("Student not found!");
                    }
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("Please enter valid numeric values.");
        } catch (SQLException e) {
            System.out.println("Error updating student: " + e.getMessage());
        }
    }

    public void sortStudentsByMarks() {
        String sql = "SELECT * FROM students ORDER BY marks DESC";

        try (Connection con = DBConnection.getConnection();
             java.sql.PreparedStatement ps = con.prepareStatement(sql);
             java.sql.ResultSet rs = ps.executeQuery()) {
                System.out.println("\n===========STUDENTS SORTED BY MARKS===========");
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.println("ID: " + rs.getInt("id"));

                    System.out.println("Name: " + rs.getString("name"));

                    System.out.println("age: " + rs.getInt("age"));

                    System.out.println("Course: " + rs.getString("course"));

                    System.out.println("Marks: " + rs.getDouble("marks"));

                    System.out.println("--------------------");
                }

                if (!found) {
                    System.out.println("No students found!");
                }
             } catch (java.sql.SQLException e) {
                System.out.println("Error sorting students!");
                e.printStackTrace();
             }
    }

    public void showStudentStatistics() {
        String sql = "SELECT COUNT(*) AS total_students, " + "NVL(AVG(marks), 0) AS average_marks FROM students";
        try (Connection con = DBConnection.getConnection()) {
            if (con == null) {
                System.out.println("Database connection failed!");
                return;
            }
            try (java.sql.PreparedStatement ps = con.prepareStatement(sql);
                 java.sql.ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("\n============STUDENT STATISTICS============");
                    System.out.println("Total Students: " + rs.getInt("total_students"));
                    System.out.println("Average Marks: " + rs.getDouble("average_marks"));
                }
            }
        }  catch (java.sql.SQLException e) {
            System.out.println("Error retrieving student statistics!");
            e.printStackTrace();
        }
    }
    public void searchStudentByName() {
        String name;
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter student name to search: ");
            name = sc.nextLine().trim();
        }

        if (name.isEmpty()) {
            System.out.println("Student name cannot be empty!");
            return;
        }
      String sql = "SELECT * FROM students WHERE LOWER(name) LIKE LOWER(?)";

      try (Connection con = DBConnection.getConnection()) {
        if (con == null) {
            System.out.println("Database connection failed!");
            return;
        }
        try (java.sql.PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            try (java.sql.ResultSet rs = ps.executeQuery()) {
                boolean found = false;
                while (rs.next()) {
                    found = true;

                    System.out.println("\nID: " + rs.getInt("id"));
                    System.out.println("Name: " + rs.getString("name"));
                    System.out.println("Age: " + rs.getInt("age"));
                    System.out.println("Course: " + rs.getString("course"));
                    System.out.println("Marks: " + rs.getDouble("marks"));

                    System.out.println("---------------------");
                }
                if (!found) {
                    System.out.println("No matching students found!");
                }
            }
        }
    }    catch (java.sql.SQLException e) {
             System.out.println("Error searching for student!");
              e.printStackTrace();
        }
    }

}