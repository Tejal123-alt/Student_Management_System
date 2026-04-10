import java.io.*;
import java.util.*;

public class StudentManager {
    private static final String FILE_NAME = "students.dat";

    // Add Student
    public void addStudent(Student s) {
        try {
            ArrayList<Student> list = readStudents();

            for (int i = 0; i < list.size(); i++) {
                Student st = list.get(i);

                if (st.getId() == s.getId()) {
                    System.out.println("ID already exists!");
                    return;
                }
            }

            list.add(s);

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            oos.writeObject(list);
            oos.close();

            System.out.println("Student Added!");

        } catch (Exception e) {
            System.out.println("Error Occured: " + e);
        }
    }

    // Read Students
    public ArrayList<Student> readStudents() {
        ArrayList<Student> list = new ArrayList<>();

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME));
            list = (ArrayList<Student>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            // File may not exist
            // System.out.print("File may not exist!");
        }

        return list;
    }

    // View Students
    public void viewStudents() {
        ArrayList<Student> list = readStudents();

        if (list.isEmpty()) {
            System.out.println("No records found!");
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            Student s = list.get(i);
            s.display();
        }
    }

    // Search Student
    public void searchStudent(int id) {
        ArrayList<Student> list = readStudents();

        for (int i = 0; i < list.size(); i++) {
            Student s = list.get(i);

            if (s.getId() == id) {
                System.out.println("Student Found:");
                s.display();
                return;
            }
        }

        System.out.println("Student not found!");
    }

    // Update Student
    public void updateStudent(int id, String name, double marks) {
        ArrayList<Student> list = readStudents();
        boolean found = false;

        for (int i = 0; i < list.size(); i++) {
            Student s = list.get(i);

            if (s.getId() == id) {
                s.setName(name);
                s.setMarks(marks);
                found = true;
            }
        }

        if (found) {
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
                oos.writeObject(list);
                oos.close();
                System.out.println("Updated Successfully!");
            } catch (Exception e) {
                System.out.println("Error Occured: "+e);
            }
        } else {
            System.out.println("Student not found!");
        }
    }

    // Delete Student
    public void deleteStudent(int id) {
        ArrayList<Student> list = readStudents();
        boolean removed = false;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                list.remove(i);
                removed = true;
                break;
            }
        }

        if (removed) {
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
                oos.writeObject(list);
                oos.close();
                System.out.println("Deleted Successfully!");
            } catch (Exception e) {
                System.out.println("Error Occured:"+e);
            }
        } else {
            System.out.println("Student not found!");
        }
    }
}