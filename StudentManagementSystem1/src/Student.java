import java.io.Serializable;

public class Student implements Serializable {
    private int id;
    private String name;
    private double marks;

    // Constructor
    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    // Getter methods
    public int getId() { return id; }
    public String getName() { return name; }
    public double getMarks() { return marks; }

    // Setter methods
    public void setName(String name) { this.name = name; }
    public void setMarks(double marks) { this.marks = marks; }

    // Custom method instead of toString()
    public void display() {
        System.out.println(id + " | " + name + " | " + marks);
    }
}