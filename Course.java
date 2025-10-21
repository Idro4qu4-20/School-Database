public class Course {
    private boolean isGraduateCourse;
    private int courseNum;
    private String courseDept;
    private int numCredits;

    public Course(boolean isGraduateCourse, int courseNum, String courseDept, int numCredits) {
        this.isGraduateCourse = isGraduateCourse;
        this.courseNum = courseNum;
        this.courseDept = courseDept;
        this.numCredits = numCredits;
    }

    public boolean isGraduateCourse() {
        return this.isGraduateCourse;
    }

    public int getCourseNum() {
        return this.courseNum;
    }

    public String getCourseDept() {
        return this.courseDept;
    }

    public int getNumCredits() {
        return this.numCredits;
    }
    
    public String getCourseName() {
        if (this.isGraduateCourse) {
            return "G" + courseDept + courseNum;
        } else {
            return "U" + courseDept + courseNum;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Course)) {
            return false;
        }
        Course course = (Course) o;
        return isGraduateCourse == course.isGraduateCourse && courseNum == course.courseNum && Objects.equals(courseDept, course.courseDept) && numCredits == course.numCredits;
    }
    
    @Override
    public String toString() {
        System.out.printf("Course: %3s-%3d | Number of Credits: %02d  | Graduate/Undergraduate", courseDept, courseNum, numCredits, isGraduateCourse)
    }
    
    @Override
    public int compareTo(Course otherCourse) {
        int graduateComparison = Boolean.compare(this.isGraduateCourse, otherCourse.isGraduateCourse);
        if (graduateComparison != 0) {
            return graduateComparison;
        }
        return Integer.compare(this.courseNum, otherCourse.courseNum);
    
}