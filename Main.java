import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Create manager
        StudentManager manager = new StudentManager();

        // 🔥 LOAD DATA FROM FILE
        manager.loadFromFile();

        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Enter ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter Name: ");
                String name = sc.nextLine();

                System.out.print("Enter Age: ");
                int age = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter Course: ");
                String course = sc.nextLine();

                manager.addStudent(new Student(id, name, age, course));
                System.out.println("Student Added");

            } else if (choice == 2) {
                System.out.println(manager.viewStudents());

            } else if (choice == 3) {
                System.out.print("Enter ID to search: ");
                int id = sc.nextInt();

                Student s = manager.searchStudent(id);

                if (s != null)
                    System.out.println("Found: " + s);
                else
                    System.out.println("Student not found");

            } else if (choice == 4) {
                System.out.print("Enter ID to delete: ");
                int id = sc.nextInt();

                if (manager.deleteStudent(id))
                    System.out.println("Student Deleted");
                else
                    System.out.println("Student not found");

            } else if (choice == 5) {
                System.out.println("Exiting...");
                break;

            } else {
                System.out.println("Invalid choice");
            }
        }

        sc.close();
    }
}