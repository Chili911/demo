import java.util.ArrayList;
import java.util.List;

    public class Student {

        private final String StudentID;
        String FullName;
        public List<Course> courses; // List of courses the student is enrolled in


        public Student(String StudentID , String FullName, List<Course> courses) {
            this.StudentID = StudentID;
            this.FullName = FullName;
            this.courses = courses;

        }


        // Getter methods
        public String getStudentID() {
            return StudentID;
        }

        public String getFullName() {
            return FullName;
        }


        // Method to get the list of courses for the student
        public List<Course> getCourses() {
            return courses;
        }




        public void setFullName(String newName) {

        }


        public void setStudentID(String newID) {
        }

    }
