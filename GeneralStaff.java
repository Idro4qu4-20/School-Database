public class GeneralStaff extends Employee {
    private String duty = "";

    public GeneralStaff() {
        super();
    }

    public GeneralStaff(String duty){
        super();
        this.duty = duty;
    }

    public GeneralStaff(String deptName, String duty){
        super(deptName);
        this.duty = duty;
    }

    public GeneralStaff(String name, int birthYear, String deptName, String duty){
        super(name, birthYear, deptName);
        this.duty = duty;
    }

    public String getDuty(){
        return this.duty;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GeneralStaff)) {
            return false;
        }
        GeneralStaff other = (GeneralStaff) obj;
        return super.equals(other) && duty.equals(other.duty);
    }

    public String toString() {
        return String.format("%s | Duty: %10s", super.toString(), duty);
    }
}