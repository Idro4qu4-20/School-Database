public class Faculty extends Employee {
    private Course[] coursesTaught = new Course[100];
    private int numCoursesTaught = 0;
    private boolean isTenured = false;

    public Faculty() {
        super();
    }

    public Faculty(boolean isTenured) {
        super();
        this.isTenured = isTenured;
    }

    public Faculty(String deptName, boolean isTenured) {
        super(deptName);
        this.isTenured = isTenured;
    }

    public Faculty(String name , int birthYear, String deptName, boolean isTenured) {
        super(name, birthYear, deptName);
        this.isTenured = isTenured;
    }

    public boolean isTenured() {
        return isTenured;
    }

    public int getNumCoursesTaught() {
        return numCoursesTaught;
    }

    public void setIsTenured(boolean isTenured) {
        this.isTenured = isTenured;
    }

    public void addCourseTaught(Course courseToAdd) {
        if (numCoursesTaught < coursesTaught.length) {
            coursesTaught[numCoursesTaught++] = courseToAdd;
        }
    }

    public void addCoursesTaught(Course[] coursesToAdd) {
        for (Course course : coursesToAdd) {
            addCourseTaught(course);
        }
    }

    public Course getCourseTaught(int courseIndex) {
        if (courseIndex < 0 || courseIndex >= this.numCoursesTaught) {
            return null;
        }
        return this.coursesTaught[courseIndex];
    }

    public String getCourseTaughtAsString(int courseIndex) {
        if (courseIndex < 0 || courseIndex >= numCoursesTaught) {
            return "";
        }
        Course course = getCourseTaught(courseIndex);
        return course.getCourseDept() + "-" + String.format("%03d", course.getCourseNum());
    }

    public String getAllCoursesTaughtAsString(){
        String courseList = "";
        for (int i = 0; i < numCoursesTaught; i++) {
            courseList += getCourseTaughtAsString(i) + ", ";
        }
        return courseList;
    }

    public boolean teachesCourse(Course course) {
        for (int i = 0; i < numCoursesTaught; i++) {
            if (coursesTaught[i] != null && coursesTaught[i].equals(course)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Faculty)) {
            return false;
        }

        Faculty other = (Faculty) obj;
        return super.equals(other) && isTenured == other.isTenured();
    }

    @Override
    public String toString() {
        return String.format("%s Faculty: %11s | Number of Courses Taught: %3d | Courses Taught: %s", super.toString(), (isTenured) ? "Is Tenured" : "Not Tenured", numCoursesTaught, getAllCoursesTaughtAsString());
    }

    @Override
    public int compareTo(Person p) {
        return Integer.compare(this.numCoursesTaught, ((Faculty)p).numCoursesTaught);
    }

}
