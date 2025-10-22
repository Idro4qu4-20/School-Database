public class Person {
    private String name;
    private int birthYear;

    public Person(){
        this.name = "";
        this.birthYear = 0;
    }

    public Person(String name, int birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }

    public String getName() {
        return name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setName(String name) {
        if (name == null) {
            name = "";
        }
        this.name = name;
    }

    public void setBirthYear(int birthYear) {
        if (birthYear < 0) {
            birthYear = 0;
        }
        this.birthYear = birthYear;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Person)) {
            return false;
        }
        Person other = (Person) obj;
        return name.equals(other.name) && birthYear == other.birthYear;
    }

    @Override
    public String toString() {
        return String.format("Person: Name: %30s | Birth Year: %4d", name, birthYear);
    }

    @Override
    public int compareTo(Person p) {
        return Integer.compare(birthYear, p.birthYear);
    }
}