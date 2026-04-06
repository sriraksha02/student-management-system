import java.util.*;
import java.io.*;

public class StudentManager {
    ArrayList<Student> list = new ArrayList<>();
    private final String FILE_NAME = "students.txt";

    // 🔁 LOAD FROM FILE
    public void loadFromFile() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) return;

            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split(",");

                int id = Integer.parseInt(data[0]);
                String name = data[1];
                int age = Integer.parseInt(data[2]);
                String course = data[3];

                list.add(new Student(id, name, age, course));
            }
            sc.close();

        } catch (Exception e) {
            System.out.println("Error loading file");
        }
    }

    // 💾 SAVE TO FILE
    public void saveToFile() {
        try {
            PrintWriter pw = new PrintWriter(FILE_NAME);

            for (Student s : list) {
                pw.println(s.getId() + "," + s.getName() + "," +
                           s.getAge() + "," + s.getCourse());
            }

            pw.close();

        } catch (Exception e) {
            System.out.println("Error saving file");
        }
    }

    // ➕ ADD
    public void addStudent(Student s) {
        list.add(s);
        saveToFile();
    }

    // 📋 VIEW
    public String viewStudents() {
        if (list.isEmpty()) return "No students found";

        String result = "";
        for (Student s : list) {
            result += s + "\n";
        }
        return result;
    }

    // 🔍 SEARCH
    public Student searchStudent(int id) {
        for (Student s : list) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    // ❌ DELETE
    public boolean deleteStudent(int id) {
        Iterator<Student> it = list.iterator();

        while (it.hasNext()) {
            if (it.next().getId() == id) {
                it.remove();
                saveToFile();
                return true;
            }
        }
        return false;
    }

    // ✏️ UPDATE ✅
    public boolean updateStudent(int id, String name, int age, String course) {
        for (Student s : list) {
            if (s.getId() == id) {
                s.setName(name);
                s.setAge(age);
                s.setCourse(course);

                saveToFile(); // save updated data
                return true;
            }
        }
        return false;
    }
}