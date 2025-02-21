public class Course {

    private  final String CourseId; // Id is fixed in length
    public String Coursename;

    public Course(String CourseId , String Coursename) {

        if (CourseId.length() != 5) {
            throw new IllegalArgumentException("Course ID must be exactly 6 characters long");
        }

        this.CourseId = CourseId;
        this.Coursename = Coursename;
    }

    // Getter methods for getting CourseID AND Coursename
    public String getCourseId() {
        return CourseId;
    }

    public String getCoursename() {
        return Coursename;
    }


    public static void main(String[] args) {
        // Creating Course objects
        Course Algorithms = new Course("ME10K", "Algorithms");
        Course DataStructures = new Course("JKLM2", "Data Structures");
        Course Webdevelp = new Course("PQP03", "Web development");
        Course ComputerArchitecture = new Course("YBO82", "Computer architecture");



    }

}
