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
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Course)) {
            return false;
        }
        Course other = (Course) obj;
        return isGraduateCourse == other.isGraduateCourse
                && courseNum == other.courseNum
                && Objects.equals(courseDept, other.courseDept)
                && numCredits == other.numCredits;
    }
    
    @Override
    public String toString() {
        String courseType = isGraduateCourse ? "Graduate" : "Undergraduate";
        return String.format("Course: %s-%03d | Number of Credits: %02d  | %s", courseDept, courseNum, numCredits, courseType);
    }
    
    @Override
    public int compareTo(Course otherCourse) {
        int graduateComparison = Boolean.compare(this.isGraduateCourse, otherCourse.isGraduateCourse);
        if (graduateComparison != 0) {
            return graduateComparison;
        }
        return Integer.compare(this.courseNum, otherCourse.courseNum);
    
    }
}