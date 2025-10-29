public class Student extends Person {
    private static int numStudents;
    private int studentID = 1;
    private Course[] coursesTaken = new Course[50];
    private int numCoursesTaken = 0;
    private boolean isGraduate = false;
    private String major = "undeclared";

    public Student() {
        super();
        this.studentID = numStudents + 1;
        numStudents++;
    }

    public Student(boolean isGraduate) {
        super();
        this.isGraduate = isGraduate;
        this.studentID = numStudents + 1;
        numStudents++;
    }

    public Student(String major, boolean isGraduate){
        super();
        this.major = major;
        this.isGraduate = isGraduate;
        this.studentID = numStudents + 1;
        numStudents++;
    }

    public Student(String name, int birthYear, String major, boolean isGraduate) {
        super(name, birthYear);
        this.major = major;
        this.isGraduate = isGraduate;
        this.studentID = numStudents + 1;
        numStudents++;
    }

    public boolean isGraduate() {
        return isGraduate;
    }

    public int getNumCoursesTaken() {
        return numCoursesTaken;
    }

    static public int getNumStudents() {
        return numStudents;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getMajor() {
        return major;
    }

    public void setIsGraduate(boolean isGraduate) {
        this.isGraduate = isGraduate;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void addCourseTaken(Course course) {
        if (numCoursesTaken < coursesTaken.length) {
            coursesTaken[numCoursesTaken++] = course;
        }
    }

    public void addCoursesTaken(Course[] courses) {
        for (Course course : courses) {
            addCourseTaken(course);
        }
    }

    public Course getCourseTaken(int courseIndex) {
        if (courseIndex < 0 || courseIndex >= numCoursesTaken) {
            return null;
        }
        return this.coursesTaken[courseIndex];
    }

    public String getCourseTakenAsString(int courseIndex) {
        if (courseIndex < 0 || courseIndex >= numCoursesTaken) {
            return "";
        }
        Course course = getCourseTaken(courseIndex);
        return course.getCourseDept() + "-" + course.getCourseNum();
    }

    public String getAllCoursesTakenAsString(){
        String courseList = "";
        for (int i = 0; i < numCoursesTaken; i++) {
            courseList += getCourseTakenAsString(i) + ", ";
        }
        return courseList;
    }

    public int getNumCredits() {
        int totalCredits = 0;
        for (int i = 0; i < numCoursesTaken; i++) {
            totalCredits += coursesTaken[i].getNumCredits();
        }
        return totalCredits;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Student)) {
            return false;
        }
        Student other = (Student) obj;
        return super.equals(other) && major.equals(other.major) && isGraduate == other.isGraduate;
    }

    @Override
    public String toString() {
        return String.format("%s Student: studentID: %04d | Major %20s | %14s | Number of Courses Taken: %3d | Courses Taken: %s", super.toString(), studentID, major, isGraduate ? "Graduate" : "Undergraduate", numCoursesTaken, getAllCoursesTakenAsString());
    }

    @Override
    public int compareTo(Person p) {
        return this.getNumCredits() - ((Student)p).getNumCredits();
    }
}
