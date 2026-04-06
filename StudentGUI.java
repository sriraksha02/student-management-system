import java.awt.*;
import javax.swing.*;

public class StudentGUI extends JFrame {
    private JTextField idField, nameField, ageField, courseField;
    private JTextArea outputArea;
    private StudentManager manager;

    public StudentGUI() {
        manager = new StudentManager();
        manager.loadFromFile();

        setTitle("Student Management System");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 🔷 Main panel
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 🔷 Form panel (labels + fields)
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        formPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        formPanel.add(idField);

        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        formPanel.add(ageField);

        formPanel.add(new JLabel("Course:"));
        courseField = new JTextField();
        formPanel.add(courseField);

        // 🔷 Buttons panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        JButton addBtn = new JButton("Add");
        JButton viewBtn = new JButton("View");
        JButton searchBtn = new JButton("Search");
        JButton deleteBtn = new JButton("Delete");

        buttonPanel.add(addBtn);
        buttonPanel.add(viewBtn);
        buttonPanel.add(searchBtn);
        buttonPanel.add(deleteBtn);

        // 🔷 Output area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // 🔷 Add to main panel
        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);

        // 🔘 Button Actions

        // ADD
        addBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String course = courseField.getText();

                manager.addStudent(new Student(id, name, age, course));
                outputArea.setText("Student Added Successfully!");

            } catch (Exception ex) {
                outputArea.setText("Invalid Input!");
            }
        });

        // VIEW
        viewBtn.addActionListener(e -> {
            outputArea.setText(manager.viewStudents());
        });

        // SEARCH
        searchBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                Student s = manager.searchStudent(id);

                if (s != null)
                    outputArea.setText(s.toString());
                else
                    outputArea.setText("Student Not Found");

            } catch (Exception ex) {
                outputArea.setText("Enter valid ID");
            }
        });

        // DELETE
        deleteBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());

                if (manager.deleteStudent(id))
                    outputArea.setText("Deleted Successfully");
                else
                    outputArea.setText("Student Not Found");

            } catch (Exception ex) {
                outputArea.setText("Enter valid ID");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new StudentGUI();
    }
}