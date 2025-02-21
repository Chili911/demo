import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
import java.util.Scanner;
import java.io.*;
import java.util.*;

public class Main {
    private static final String FILE_NAME = "students.bin";

    // C:\Users\RK\Desktop\Project\out\production\Project

    public static void main(String[] args) {
        // Read students from the binary file
        List<Student> students = readStudentsFromFile();

        showMenu(students);

        try {
            // Create a FileOutputStream to write data to the file
            FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME);
            fileOutputStream.close();

            System.out.println("Students written successfully to file.");
        } catch (IOException e) {
            System.out.println("Error writing students to file: " + e.getMessage());
        }

        // Read students from the binary file and print them
        List<Student> readStudents = readStudentsFromFile();
        for (Student student : readStudents) {
            System.out.println(student);
        }


        // Check if the file exists
        if (fileExists(FILE_NAME)) {
            // File exists, proceed with reading
            System.out.println("File exists. Reading students...");
            // Read students from the file
            // readStudentsFromFile();
        } else {
            // File doesn't exist, display an error message
            System.out.println("File does not exist: " + FILE_NAME);
        }
    }

    // Function to check if the file exists
    private static boolean fileExists(String fileName) {
        File file = new File(fileName);
        return file.exists() && !file.isDirectory();

    }



    // Function to read students from the file
    private static List<Student> readStudentsFromFile() {
        List<Student> students = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(FILE_NAME)) {
            StringBuilder sb = new StringBuilder();
            int data;
            boolean isId = true;
            int id = 0;
            String name = "";

            while ((data = fis.read()) != -1) {
                if (data == ',') { // comma separates id and name
                    isId = false;
                    continue;
                }

                if (data == ';') { // semicolon separates students
                    students.add(new Student(Integer.toString(id), name, null)); // add student to the list
                    isId = true;
                    id = 0;
                    name = "";
                } else {
                    if (isId) {
                        id = id * 10 + (data - '0'); // converting byte to integer
                    } else {
                        name += (char) data; // converting byte to character
                    }
                }
            }
            System.out.println("Students loaded successfully from file.");
        } catch (IOException e) {
            System.out.println("Error loading students from file: " + e.getMessage());
        }
        return students;
    }





    // function to display menu
    private static void showMenu(List<Student> students) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Create a Student");
            System.out.println("2. Add To List");
            System.out.println("3. Search Students by ID or Name");
            System.out.println("4. Delete a Student By ID");
            System.out.println("5. Update Student ID");
            System.out.println("6. Print full names of the students in list");
            System.out.println("7. Print Courses Student is taking");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {

                case 1:
                    createStudent(scanner, students);
                    break;


                case 2:
                    Student newStudent = createStudent(scanner, students); // Create a new student
                    addToList(students, newStudent); // Add the new student to the list
                    break;


                case 3:
                    System.out.print("Enter student ID or name to search: ");
                    String searchQuery = scanner.nextLine();
                    searchStudents(students, searchQuery);
                    break;

                case 4:
                    System.out.print("Enter student ID to delete: ");
                    String deleteId = scanner.nextLine();
                    deleteStudentById(students, deleteId);
                    break;

                case 5:
                    updateStudentID(scanner, students);
                    break;



                case 6:
                    printStudentNames(students);
                    break;


                case 7:
                    System.out.print("Enter student ID to view their courses: ");
                    int studentID = scanner.nextInt();
                    printCoursesById(studentID);
                    break;

                case 8:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 8);

        scanner.close();

    }


    /*
private static void printStudentCourses(List<Student> students, String studentID) {
		// TODO Auto-generated method stub

	}
 */


    // Function to create a new student
    private static Student createStudent(Scanner scanner, List<Student> students) {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        int id = generateUniqueID(students); // generate unique ID
        Student newStudent = new Student(Integer.toString(id), name, new ArrayList<>()); // Create new student
        students.add(newStudent); // Add student to the list
        System.out.println("Student created successfully with ID: " + id);
        return newStudent;
    }


    // Function to generate a unique ID for a student
    private static int generateUniqueID(List<Student> students) {
        Random random = new Random();
        int id;
        boolean isUnique;
        do {
            // Generate a random number between 100000 and 999999 (inclusive)
            id = 100000 + random.nextInt(900000);
            isUnique = true;
            for (Student student : students) {
                if (Integer.parseInt(student.getStudentID()) == id) { // check for uniqueness
                    isUnique = false;
                    break;
                }
            }
        } while (!isUnique);
        return id;
    }

    // Function to add a student to the list
    public static void addToList(List<Student> students, Student student) {
        if (student != null) {
            students.add(student);
            System.out.println("Student added successfully.");
        } else {
            System.out.println("Cannot add null student to the list.");
        }
    }



    //// Function for searching for student by Id or Name
    private static List<Student> searchStudents(List<Student> students, String searchQuery) {
        List<Student> matchingStudents = new ArrayList<>();

        // Trim leading and trailing whitespace from the search query
        searchQuery = searchQuery.trim().toLowerCase(); // Convert to lowercase for case-insensitive comparison

        // Iterate through the list of students
        for (Student student : students) {
            // Trim leading and trailing whitespace from the student name and convert to lowercase
            String studentName = student.getFullName().trim().toLowerCase();

            // Check if student's ID or full name contains the search query (case-insensitive comparison)
            if (student.getStudentID().toLowerCase().contains(searchQuery) || studentName.contains(searchQuery)) {
                // If match is found, add student to the list of matching students
                matchingStudents.add(student);
            }
        }

        // Print IDs and full names of matching students
        if (matchingStudents.isEmpty()) {
            System.out.println("No matching students found.");
        } else {
            System.out.println("Matching students:");
            for (Student student : matchingStudents) {
                System.out.println("ID: " + student.getStudentID() + ", Full Name: " + student.getFullName());
            }
        }
        return matchingStudents;
    }

    // Function to delete a student from the list based on ID
    private static void deleteStudentById(List<Student> students, String studentId) {
        boolean studentFound = false;

        // Iterate through the list of students
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            // Check if the student's ID matches the provided ID
            if (student.getStudentID().equals(studentId)) {
                iterator.remove(); // Remove the student from the list
                studentFound = true;
                System.out.println("Student with ID " + studentId + " deleted successfully.");
                break;
            }
        }

        if (!studentFound) {
            System.out.println("No student found with ID " + studentId + ".");
        }
    }

    private static void updateStudentID(Scanner scanner, List<Student> students) {
        // Prompt the user to enter the ID of the student to update
        System.out.print("Enter the ID of the student to update: ");
        String studentIDToUpdate = scanner.nextLine();

        // Search for the student by ID
        Student foundStudent = null;
        for (Student student : students) {
            if (student.getStudentID().equals(studentIDToUpdate)) {
                foundStudent = student;
                break;
            }
        }

        if (foundStudent != null) {
            // Prompt the user to enter the new ID
            System.out.print("Enter the updated ID for student " + foundStudent.getFullName() + ": ");
            String updatedID = scanner.nextLine();

            // Update the student's ID
            foundStudent.setStudentID(updatedID);

            System.out.println("Student ID updated successfully.");
        } else {
            System.out.println("Student with ID " + studentIDToUpdate + " not found.");
        }
    }


    // function that prints only the full names of the students stored in the list:
    public static void printStudentNames(List<Student> students) {
        System.out.println("Full Names of Students:");
        for (Student student : students) {
            System.out.println(student.getFullName());
        }
    }

    //function to print the courses based on id
    private static String[][] studentCourses = {
            {"Machine Learning", "Computer Network"},
            {"Data Structure", "Operating System"},
            {"Computer Science", "Database"}
    };

    // Function to print courses based on student id
    public static void printCoursesById(int studentId) {
        // Check if the provided student ID is within the valid range
        if (studentId >= 1 && studentId <= studentCourses.length) {
            // Retrieve the courses for the given student ID
            String[] courses = studentCourses[studentId - 1];
            // Print the courses
            System.out.println("Courses for student ID " + studentId + ":");
            for (String course : courses) {
                System.out.println(course);
            }
        } else {
            // Print a message if the student ID is invalid
            System.out.println("No courses found for student ID " + studentId);
        }
    }


}