import java.util.Scanner;

public class StudentManagementSystem {
    public static void main(String[]args) {
        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();
        while (true) {
            System.out.println("\n============STUDENT MANAGEMENT SYSTEM============");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Sort Students by Marks");
            System.out.println("7. Student Statistics");
            System.out.println("8. Search Student by Name");
            System.out.println("9. Exit");

            System.out.print("Enter your choice: ");
         
            if (!sc.hasNextLine()) {
                System.out.println("Input closed. Please restart the program.");
                sc.close();
                return;
            }
            String input = sc.nextLine().trim();

            
            switch (input) {
                case "1":
                    dao.addStudent();
                    break;

                    case "2":
                        dao.viewStudents();
                        break;

                        case "3":
                            dao.searchStudent();
                            break;

                            case "4":
                                dao.updateStudent();
                                break;

                                case "5":
                                    dao.deleteStudent();
                                    break;

                                    case "6":
                                        dao.sortStudentsByMarks();
                                        break;

                                        case "7":
                                            dao.showStudentStatistics();
                                            break;

                                            case "8":
                                                dao.searchStudentByName();
                                                break;

                                                    case "9":
                                                        System.out.println("Thank you for using Student Management System!");
                                                        sc.close();
                                                          return;

                                                                default:
                                                                    System.out.println("Invaild choice! Please enter 1-6.");
            }   
        }
    }
}
