/**
 * Name : Netanel Shoshan
 * This class represent the student.
 */

public class Student implements Comparable<Student> {
    private int id;
    private String name;
    private double grade;

    public Student(int id, String name, double grade) {
        this.name = name;
        this.grade = grade;
        this.id = id;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(Student o) {
        if (this.grade < o.grade)
            return -1;
        return (this.grade == o.grade) ? 0 : 1;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Student) {
            Student st = (Student) o;
            return this.id == st.id && this.grade == st.grade && this.name == st.name;
        }
        return super.equals(o);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}
