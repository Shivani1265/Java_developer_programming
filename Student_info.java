import java.util.ArrayList;
import java.util.Scanner;

// Student class to model the student entity
class Student {
    private String id;
    private String name;
    private int age;
    private String course;

    public Student(String id, String name, int age, String course) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCourse() {
        return course;
    }

    // Method to display student details
    public void displayStudentInfo() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Course: " + course);
    }
}

// StudentDatabase class to manage a collection of students
class StudentDatabase {
    private ArrayList<Student> students;

    public StudentDatabase() {
        students = new ArrayList<>();
    }

    // Add a new student
    public void addStudent(Student student) {
        students.add(student);
    }

    // Display all students
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students in the database.");
        } else {
            for (Student student : students) {
                student.displayStudentInfo();
                System.out.println("---------------------");
            }
        }
    }

    // Find a student by ID
    public Student findStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }
}

// Main class for the entry point of the system
public class Student_info {
    public static void main(String[] args) {
        StudentDatabase studentDatabase = new StudentDatabase();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("=== Student Information System ===");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Student Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter Student Course: ");
                    String course = scanner.nextLine();

                    // Add the student to the database
                    Student student = new Student(id, name, age, course);
                    studentDatabase.addStudent(student);
                    System.out.println("Student added successfully.");
                    break;

                case 2:
                    System.out.println("Displaying all students:");
                    studentDatabase.displayAllStudents();
                    break;

                case 3:
                    System.out.print("Enter Student ID to search: ");
                    String searchId = scanner.nextLine();
                    Student foundStudent = studentDatabase.findStudentById(searchId);
                    if (foundStudent != null) {
                        System.out.println("Student found:");
                        foundStudent.displayStudentInfo();
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 4);

        scanner.close();
    }
}
