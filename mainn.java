public class mainn {
    // Declaration of studentCourses array containing courses for each student
    private static String[][] studentCourses = {
            {"Machine Learning", "Computer Network"},
            {"Data Structure", "Operating System"},
            {"Computer Science", "Database"}
    };

    // Function to print courses based on student ID
    public static void printCoursesById(int studentId) {
        // Convert studentId to index (subtract 1 as arrays are zero-indexed)
        int index = studentId - 1;

        // Check if the index is within the valid range of studentCourses array
        if (index >= 0 && index < studentCourses.length) {
            // Retrieve the courses for the given student ID
            String[] courses = studentCourses[index];
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

    public static void main(String[] args) {
        // Example usage of the printCoursesById function
        printCoursesById(1); // Print courses for student ID 1
    }
}
