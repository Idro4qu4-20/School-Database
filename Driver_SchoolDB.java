//https://youtu.be/oNtu4Z6pXgs

import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.StandardCharsets;

public class Driver_SchoolDB {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userValue = "";
        String fileName = "SchoolDB_Initial.txt";
        Optional<Path> filePath = findFile(Paths.get(System.getProperty("user.dir")), fileName);
        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<Faculty> faculties = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<GeneralStaff> generalStaff = new ArrayList<>();
        if (filePath.isPresent()) {
            try {
                String fileContent = new String(Files.readAllBytes(filePath.get()));
                System.out.println(fileContent);


                String[] lines = fileContent.split("\\n");
                for (String line : lines) {
                    line = line.trim();
                    if (line.isEmpty()) continue;
                    if (line.startsWith("Course:")) {
                        String[] parts = line.split(": ");
                        if (parts.length > 1) {
                            String[] info = parts[1].split(",");
                            if (info.length >= 4) {
                                boolean grad = Boolean.parseBoolean(info[0].trim());
                                int num = Integer.parseInt(info[1].trim());
                                String dept = info[2].trim();
                                int cred = Integer.parseInt(info[3].trim());
                                courses.add(new Course(grad, num, dept, cred));
                            }
                        }
                    } else if (line.startsWith("Faculty:")) {
                        String[] parts = line.split(": ");
                        if (parts.length > 1) {
                            String[] info = parts[1].split(",");
                            if (info.length == 4) {
                                String name = info[0].trim();
                                int year = Integer.parseInt(info[1].trim());
                                String dept = info[2].trim();
                                boolean tenured = Boolean.parseBoolean(info[3].trim());
                                faculties.add(new Faculty(name, year, dept, tenured));
                            } else if (info.length == 2) {
                                String dept = info[0].trim();
                                boolean tenured = Boolean.parseBoolean(info[1].trim());
                                faculties.add(new Faculty(dept, tenured));
                            } else if (info.length == 1) {
                                boolean tenured = Boolean.parseBoolean(info[0].trim());
                                faculties.add(new Faculty(tenured));
                            }
                        } else {
                            faculties.add(new Faculty());
                        }
                    } else if (line.startsWith("Student:")) {
                        String[] parts = line.split(": ");
                        if (parts.length > 1) {
                            String[] info = parts[1].split(",");
                            if (info.length == 4) {
                                String name = info[0].trim();
                                int year = Integer.parseInt(info[1].trim());
                                String major = info[2].trim();
                                boolean grad = Boolean.parseBoolean(info[3].trim());
                                students.add(new Student(name, year, major, grad));
                            } else if (info.length == 2) {
                                String major = info[0].trim();
                                boolean grad = Boolean.parseBoolean(info[1].trim());
                                students.add(new Student(major, grad));
                            } else if (info.length == 1) {
                                boolean grad = Boolean.parseBoolean(info[0].trim());
                                students.add(new Student(grad));
                            }
                        } else {
                            students.add(new Student());
                        }
                    } else if (line.startsWith("GeneralStaff:")) {
                        String[] parts = line.split(": ");
                        if (parts.length > 1) {
                            String[] info = parts[1].split(",");
                            if (info.length == 4) {
                                String name = info[0].trim();
                                int year = Integer.parseInt(info[1].trim());
                                String dept = info[2].trim();
                                String duty = info[3].trim();
                                generalStaff.add(new GeneralStaff(name, year, dept, duty));
                            } else if (info.length == 2) {
                                String dept = info[0].trim();
                                String duty = info[1].trim();
                                generalStaff.add(new GeneralStaff(dept, duty));
                            } else if (info.length == 1) {
                                String duty = info[0].trim();
                                generalStaff.add(new GeneralStaff(duty));
                            }
                        } else {
                            generalStaff.add(new GeneralStaff());
                        }
                    }
                }

            } catch (IOException e) {
                System.out.println("Error reading file: " + filePath.get());
            }
        } else {
            System.out.println("File not found: " + fileName);
        }

        //#region User Input
        while (!userValue.equals("exit")) {
            System.out.println("Enter a command, (Course, Faculty, GeneralStaff, Student, Save, toString, exit)");
            userValue = scanner.nextLine();
            if (userValue.equals("Exit") || userValue.equals("exit") || userValue.equals("EXIT") || userValue.equals("e") || userValue.equals("E")) {
                break;
            }

            if (userValue.equals("Course") || userValue.equals("course") || userValue.equals("COURSE") || userValue.equals("c") || userValue.equals("C")) {
                String userValue2 = "";
                while (!userValue2.equals("exit")) {
                    boolean isGrad = false;
                    System.out.println("Enter a command, (create, list, mc(minimum of all courses), mxc(maximum of all courses), exit)");
                    userValue2 = scanner.nextLine();
                    if (userValue2.equals("Exit") || userValue2.equals("exit") || userValue2.equals("EXIT") || userValue2.equals("e") || userValue2.equals("E")) {
                        break;
                    }

                    if (userValue2.equals("create") || userValue2.equals("Create") || userValue2.equals("CREATE") || userValue2.equals("c") || userValue2.equals("C")) {
                        String userValue3 = "";
                        System.out.println("Enter a command, (Graduate, Undergraduate)");
                        userValue3 = scanner.nextLine();
                        if (userValue3.equals("Graduate") || userValue3.equals("graduate") || userValue3.equals("GRADUATE") || userValue3.equals("g") || userValue3.equals("G")) {
                            isGrad = true;
                        } else if (userValue3.equals("Undergraduate") || userValue3.equals("undergraduate") || userValue3.equals("UNDERGRADUATE") || userValue3.equals("u") || userValue3.equals("U")) {
                            isGrad = false;
                        }

                        System.out.println("Enter the course number");
                        int courseNum = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter the course department");
                        String courseDept = scanner.nextLine();
                        System.out.println("Enter the number of credits");
                        int numCredits = Integer.parseInt(scanner.nextLine());
                        
                        courses.add(new Course(isGrad, courseNum, courseDept, numCredits));
                    }

                    if (userValue2.equals("list") || userValue2.equals("List") || userValue2.equals("LIST") || userValue2.equals("l") || userValue2.equals("L")) {
                        int i = 1;
                        while (i < courses.size() + 1) {
                            System.out.println(i + ": " + courses.get(i - 1));
                            i++;
                        }
                    }

                    if (userValue2.equals("mc") || userValue2.equals("Mc") || userValue2.equals("MC")) {
                        int min = Integer.MAX_VALUE;
                        for (Course c : courses) {
                            if (c.getCourseNum() < min) {
                                min = c.getCourseNum();
                            }
                        }
                        System.out.println("The course with the minimum number is: " + min);
                    }

                    if (userValue2.equals("mxc") || userValue2.equals("Mxc") || userValue2.equals("MXC")) {
                        int max = Integer.MIN_VALUE;
                        for (Course c : courses) {
                            if (c.getCourseNum() > max) {
                                max = c.getCourseNum();
                            }
                        }
                        System.out.println("The course with the maximum number is: " + max);
                    }
                
                }
                
            }

            if (userValue.equals("Faculty") || userValue.equals("faculty") || userValue.equals("FACULTY") || userValue.equals("f") || userValue.equals("F")) {
                String userValue2 = "";
                while (!userValue2.equals("exit")) {
                    boolean isTend = false;
                    System.out.println("Enter a command, (create, list, ac(add course), gc(get course), cq(course query), hct(who teaches the most courses), lct(who teaches the least courses), exit)");
                    userValue2 = scanner.nextLine();
                    if (userValue2.equals("Exit") || userValue2.equals("exit") || userValue2.equals("EXIT") || userValue2.equals("e") || userValue2.equals("E")) {
                        break;
                    }

                    if (userValue2.equals("create") || userValue2.equals("Create") || userValue2.equals("CREATE") || userValue2.equals("c") || userValue2.equals("C")) {
                        System.out.println("Enter a command, (tenured, not tenured)");
                        userValue2 = scanner.nextLine();
                        if (userValue2.equals("tenured") || userValue2.equals("Tenured") || userValue2.equals("TENURED") || userValue2.equals("t") || userValue2.equals("T")) {
                            isTend = true;
                        } else if (userValue2.equals("not tenured") || userValue2.equals("Not Tenured") || userValue2.equals("NOT TENURED") || userValue2.equals("n") || userValue2.equals("N")) {
                            isTend = false;
                        }

                        System.out.println("Enter the faculty name");
                        String name = scanner.nextLine();
                        System.out.println("Enter the faculty birth year");
                        int birthYear = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter the faculty department name");
                        String deptName = scanner.nextLine();
                        faculties.add(new Faculty(name, birthYear, deptName, isTend));
                    }

                    if (userValue2.equals("list") || userValue2.equals("List") || userValue2.equals("LIST") || userValue2.equals("l") || userValue2.equals("L")) {
                        int i = 1;
                        while (i < faculties.size() + 1) {
                            System.out.println(i + ": " + faculties.get(i - 1));
                            i++;
                        }
                    }

                    if (userValue2.equals("ac") || userValue2.equals("Ac") || userValue2.equals("AC") || userValue2.equals("a") || userValue2.equals("A")) {
                        System.out.println("Enter the faculty ID");
                        int facultyID = Integer.parseInt(scanner.nextLine()) - 1;
                        String isArray = "";
                        System.out.println("Is the course an array? (yes/no)");
                        isArray = scanner.nextLine();
                        if (isArray.equals("yes") || isArray.equals("Yes") || isArray.equals("YES") || isArray.equals("y") || isArray.equals("Y")) {
                            System.out.println("How many courses do you want to add?");
                            int numCourses = Integer.parseInt(scanner.nextLine());
                            ArrayList<Course> coursesToAdd = new ArrayList<>();
                            for (int i = 0; i < numCourses; i++) {
                                System.out.println("Enter the course number (from the list)");
                                int courseIndex = Integer.parseInt(scanner.nextLine()) - 1;
                                if (courseIndex >= 0 && courseIndex < courses.size()) {
                                    coursesToAdd.add(courses.get(courseIndex));
                                } else {
                                    System.out.println("Invalid course number.");
                                }
                            }
                            Course[] courseArray = coursesToAdd.toArray(new Course[0]);
                            faculties.get(facultyID).addCoursesTaught(courseArray);
                            System.out.println("Courses added to faculty.");
                        } else if (isArray.equals("no") || isArray.equals("No") || isArray.equals("NO") || isArray.equals("n") || isArray.equals("N")) {
                            System.out.println("Enter the course ID");
                            int courseID = Integer.parseInt(scanner.nextLine()) - 1;
                            faculties.get(facultyID).addCourseTaught(courses.get(courseID));
                        }
                    }

                    if (userValue2.equals("gc") || userValue2.equals("Gc") || userValue2.equals("GC") || userValue2.equals("g") || userValue2.equals("G")) {
                        System.out.println("Enter the faculty ID");
                        int facultyID = Integer.parseInt(scanner.nextLine()) - 1;
                        System.out.println("Enter the course index (0-based)");
                        int courseIndex = Integer.parseInt(scanner.nextLine());
                        Course course = faculties.get(facultyID).getCourseTaught(courseIndex);
                        if (course != null) {
                            System.out.println("Course at index " + courseIndex + ": " + course);
                        } else {
                            System.out.println("Invalid index: " + courseIndex);
                        }
                    }
                
                    if (userValue2.equals("cq") || userValue2.equals("Cq") || userValue2.equals("CQ")) {
                        System.out.println("Enter the course ID");
                        int courseID = Integer.parseInt(scanner.nextLine()) - 1;
                        Course course = courses.get(courseID);
                        System.out.println("Enter the faculty ID");
                        int facultyID = Integer.parseInt(scanner.nextLine()) - 1;
                        Faculty faculty = faculties.get(facultyID);
                        for (int i = 0; i < faculty.getNumCoursesTaught(); i++) {
                            if (faculty.getCourseTaught(i).equals(course)) {
                                System.out.println("Course " + course.getCourseName() + " is taught by faculty " + faculty.getName());
                                break;
                            }
                        }
                        System.out.println("Course " + course.getCourseName() + " is not taught by faculty " + faculty.getName());
                    }
                
                    if (userValue2.equals("hct") || userValue2.equals("Hct") || userValue2.equals("HCT")) {
                        int maxCourses = 0;
                        for (int i = 0; i < faculties.size(); i++) {
                            if (faculties.get(i).getNumCoursesTaught() > maxCourses) {
                                maxCourses = faculties.get(i).getNumCoursesTaught();
                            }
                        }
                        for (int i = 0; i < faculties.size(); i++) {
                            if (faculties.get(i).getNumCoursesTaught() == maxCourses) {
                                System.out.println("Faculty " + faculties.get(i).getName() + " teaches the most courses");
                            }
                        }
                    }
                
                    if (userValue2.equals("lct") || userValue2.equals("Lct") || userValue2.equals("LCT")) {
                        int minCourses = Integer.MAX_VALUE;
                        for (int i = 0; i < faculties.size(); i++) {
                            if (faculties.get(i).getNumCoursesTaught() < minCourses) {
                                minCourses = faculties.get(i).getNumCoursesTaught();
                            }
                        }
                        for (int i = 0; i < faculties.size(); i++) {
                            if (faculties.get(i).getNumCoursesTaught() == minCourses) {
                                System.out.println("Faculty " + faculties.get(i).getName() + " teaches the least courses");
                            }
                        }
                    }
                
                }
            }
        
            if (userValue.equals("GeneralStaff") || userValue.equals("generalstaff") || userValue.equals("GENERALSTAFF") || userValue.equals("gs") || userValue.equals("Gs") || userValue.equals("GS")) {
                String userValue2 = "";
                while (!userValue2.equals("exit")) {
                    System.out.println("Enter a command, (create, list, exit)");
                    userValue2 = scanner.nextLine();
                    if (userValue2.equals("Exit") || userValue2.equals("exit") || userValue2.equals("EXIT") || userValue2.equals("e") || userValue2.equals("E")) {
                        break;
                    }

                    if (userValue2.equals("create") || userValue2.equals("Create") || userValue2.equals("CREATE") || userValue2.equals("c") || userValue2.equals("C")) {
                        System.out.println("Enter the general staff name");
                        String name = scanner.nextLine();
                        System.out.println("Enter the general staff birth year");
                        int birthYear = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter the general staff department name");
                        String deptName = scanner.nextLine();
                        System.out.println("Enter the general staff duty");
                        String duty = scanner.nextLine();
                        generalStaff.add(new GeneralStaff(name, birthYear, deptName, duty));
                    }

                    if (userValue2.equals("list") || userValue2.equals("List") || userValue2.equals("LIST") || userValue2.equals("l") || userValue2.equals("L")) {
                        int i = 1;
                        while (i < generalStaff.size() + 1) {
                            System.out.println(i + ": " + generalStaff.get(i - 1));
                            i++;
                        }
                    }
                }
            }
        
            if (userValue.equals("Student") || userValue.equals("student") || userValue.equals("STUDENT") || userValue.equals("s") || userValue.equals("S")) {
                String userValue2 = "";
                while (!userValue2.equals("exit")) {
                    System.out.println("Enter a command, (create, list, ac(add course), gc(get course), smc(Student with most Credits), slc(Student with least Credits), exit)");
                    userValue2 = scanner.nextLine();
                    if (userValue2.equals("Exit") || userValue2.equals("exit") || userValue2.equals("EXIT") || userValue2.equals("e") || userValue2.equals("E")) {
                        break;
                    }

                    if (userValue2.equals("create") || userValue2.equals("Create") || userValue2.equals("CREATE") || userValue2.equals("c") || userValue2.equals("C")) {
                        System.out.println("Enter the student name");
                        String name = scanner.nextLine();
                        System.out.println("Enter the student birth year");
                        int birthYear = Integer.parseInt(scanner.nextLine());
                        System.out.println("Is graduate (true or false)");
                        boolean isGrad = Boolean.parseBoolean(scanner.nextLine());
                        System.out.println("Enter the student major");
                        String major = scanner.nextLine();
                        students.add(new Student(name, birthYear, major, isGrad));
                    }

                    if (userValue2.equals("list") || userValue2.equals("List") || userValue2.equals("LIST") || userValue2.equals("l") || userValue2.equals("L")) {
                        int i = 1;
                        while (i < students.size() + 1) {
                            System.out.println(i + ": " + students.get(i - 1));
                            i++;
                        }
                    }

                    if (userValue2.equals("ac") || userValue2.equals("Ac") || userValue2.equals("AC") || userValue2.equals("a") || userValue2.equals("A")) {
                        System.out.println("Enter the student ID");
                        int studentID = Integer.parseInt(scanner.nextLine()) - 1;
                        String isArray = "";
                        System.out.println("Is it an array of courses? (yes/no)");
                        isArray = scanner.nextLine();
                        if (isArray.equals("yes") || isArray.equals("Yes") || isArray.equals("YES") || isArray.equals("y") || isArray.equals("Y")) {
                            System.out.println("How many courses do you want to add?");
                            int numCourses = Integer.parseInt(scanner.nextLine());
                            ArrayList<Course> coursesToAdd = new ArrayList<>();
                            for (int i = 0; i < numCourses; i++) {
                                System.out.println("Enter the course number (from the list)");
                                int courseIndex = Integer.parseInt(scanner.nextLine()) - 1;
                                if (courseIndex >= 0 && courseIndex < courses.size()) {
                                    coursesToAdd.add(courses.get(courseIndex));
                                } else {
                                    System.out.println("Invalid course number.");
                                }
                            }
                            Course[] courseArray = coursesToAdd.toArray(new Course[0]);
                            students.get(studentID).addCoursesTaken(courseArray);
                            System.out.println("Courses added to student.");
                        } else if (isArray.equals("no") || isArray.equals("No") || isArray.equals("NO") || isArray.equals("n") || isArray.equals("N")) {
                            System.out.println("Enter the course ID");
                            int courseID = Integer.parseInt(scanner.nextLine()) - 1;
                            students.get(studentID).addCourseTaken(courses.get(courseID));
                        }
                    }

                    if (userValue2.equals("gc") || userValue2.equals("Gc") || userValue2.equals("GC") || userValue2.equals("g") || userValue2.equals("G")) {
                        System.out.println("Enter the student ID");
                        int studentID = Integer.parseInt(scanner.nextLine()) - 1;
                        System.out.println("Enter the course index (0-based)");
                        int courseIndex = Integer.parseInt(scanner.nextLine());
                        Course course = students.get(studentID).getCourseTaken(courseIndex);
                        if (course != null) {
                            System.out.println("Course at index " + courseIndex + ": " + course);
                        } else {
                            System.out.println("Invalid index: " + courseIndex);
                        }
                    }
                
                    if (userValue2.equals("smc") || userValue2.equals("Smc") || userValue2.equals("SMC") || userValue2.equals("s") || userValue2.equals("S")) {
                        int maxCredits = 0;
                        for (int i = 0; i < students.size(); i++) {
                            if (students.get(i).getNumCredits() > maxCredits) {
                                maxCredits = students.get(i).getNumCredits();
                            }
                        }
                        for (int i = 0; i < students.size(); i++) {
                            if (students.get(i).getNumCredits() == maxCredits) {
                                System.out.println("Student " + students.get(i).getName() + " has the most credits");
                            }
                        }
                    }
                
                    if (userValue2.equals("slc") || userValue2.equals("Slc") || userValue2.equals("SLC") || userValue2.equals("s") || userValue2.equals("S")) {
                        int minCredits = Integer.MAX_VALUE;
                        for (int i = 0; i < students.size(); i++) {
                            if (students.get(i).getNumCredits() < minCredits) {
                                minCredits = students.get(i).getNumCredits();
                            }
                        }
                        for (int i = 0; i < students.size(); i++) {
                            if (students.get(i).getNumCredits() == minCredits) {
                                System.out.println("Student " + students.get(i).getName() + " has the least credits");
                            }
                        }
                    }
                }
            }
        
            if (userValue.equals("Save") || userValue.equals("save") || userValue.equals("SAVE") || userValue.equals("s") || userValue.equals("S")) {
                try {
                    Path savePath = Paths.get(System.getProperty("user.dir"), "SchoolDB_Updated.txt");
                    saveFile(savePath, courses, faculties, students, generalStaff);
                    System.out.println("Database saved successfully to SchoolDB_Updated.txt.");
                } catch (IOException e) {
                    System.out.println("Error saving file: " + e.getMessage());
                }
            }

            if (userValue.equals("toString") || userValue.equals("tostring") || userValue.equals("TOSTRING") || userValue.equals("t") || userValue.equals("T")) {
                System.out.println("**************************************************************");
                System.out.println("SCHOOL DATABASE INFO:");
                System.out.println();
                System.out.println("************************************************");
                System.out.println("COURSES:");
                for (Course c : courses) {
                    System.out.println(c);
                }
                System.out.println("************************************************");
                System.out.println("************************************************");
                System.out.println("PERSONS:");
                System.out.println("************************************************");
                System.out.println("************************************************");
                System.out.println("EMPLOYEES:");
                System.out.println("************************************************");
                System.out.println("************************************************");
                System.out.println("GENERAL STAFF:");
                for (GeneralStaff gs : generalStaff) {
                    System.out.println(gs);
                }
                System.out.println("************************************************");
                System.out.println("************************************************");
                System.out.println("FACULTY:");
                for (Faculty f : faculties) {
                    System.out.println(f);
                }
                System.out.println("************************************************");
                System.out.println("************************************************");
                System.out.println("STUDENTS:");
                for (Student s : students) {
                    System.out.println(s);
                }
                System.out.println("************************************************");
                System.out.println("**************************************************************");
                System.out.println();
            }
        }
        scanner.close();
    }

            /**
     * Recursively searches for a file with the given name starting from the root path.
     * @param root the starting directory
     * @param fileName the name of the file to find
     * @return an Optional containing the path if found, empty otherwise
     */
    private static Optional<Path> findFile(Path root, String fileName) {
        try {
            return Files.walk(root)
                    .filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().equals(fileName))
                    .findFirst();
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    /**
     * Saves the school database to the specified file path.
     * @param filePath the path to save the file
     * @param courses list of courses
     * @param faculties list of faculties
     * @param students list of students
     * @param generalStaff list of general staff
     * @throws IOException if an I/O error occurs
     */
    private static void saveFile(Path filePath, ArrayList<Course> courses, ArrayList<Faculty> faculties, ArrayList<Student> students, ArrayList<GeneralStaff> generalStaff) throws IOException {
        List<String> lines = new ArrayList<>();
        for (Course c : courses) {
            lines.add("Course: " + c.isGraduateCourse() + ", " + c.getCourseNum() + ", " + c.getCourseDept() + ", " + c.getNumCredits());
        }
        lines.add("");
        for (Faculty f : faculties) {
            lines.add("Faculty: " + f.getName() + ", " + f.getBirthYear() + ", " + f.getDeptName() + ", " + f.isTenured());
        }
        lines.add("");
        for (Student s : students) {
            lines.add("Student: " + s.getName() + ", " + s.getBirthYear() + ", " + s.getMajor() + ", " + s.isGraduate());
        }
        lines.add("");
        for (GeneralStaff gs : generalStaff) {
            lines.add("GeneralStaff: " + gs.getName() + ", " + gs.getBirthYear() + ", " + gs.getDeptName() + ", " + gs.getDuty());
        }
        Files.write(filePath, lines, StandardCharsets.UTF_8);
    }
}

