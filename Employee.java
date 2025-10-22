public class Employee extends Person {
    private String deptName;
    private static int numEmployees;
    private int employeeID = 0;

    public Employee() {
        super();
        this.deptName = "";
        this.employeeID = numEmployees;
        numEmployees++;
    }

    public Employee(String deptName) {
        super();
        this.deptName = deptName;
        this.employeeID = numEmployees;
        numEmployees++;
    }

    public Employee(String name, int birthYear, String deptName) {
        super(name, birthYear);
        this.deptName = deptName;
        this.employeeID = numEmployees;
        numEmployees++;
    }

    public String getDeptName() {
        return this.deptName;
    }

    public static int getNumEmployees() {
        return numEmployees;
    }

    public int getEmployeeID() {
        return this.employeeID;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) obj;
        return super.equals(other) && deptName.equals(other.deptName);
    }

    @Override
    public String toString() {
        return String.format("%s | Employee: Department: %20s | Employee Number: %3d", super.toString(), deptName, employeeID);
    }

    @Override
    public int compareTo(Person p) {
        return Integer.compare(employeeID, p.employeeID);
    }
}