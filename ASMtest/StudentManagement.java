package ASMtest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class StudentManagement {
    private List<Student> students;

    public StudentManagement() {
        students = new ArrayList<>();
    }

    // Method to add a new student
    public void addStudent(Student student) {
    // Kiểm tra xem ID đã tồn tại chưa
    for (Student existingStudent : students) {
        if (existingStudent.getId().equals(student.getId())) {
            System.out.println("Error: Student with ID " + student.getId() + " already exists.");
            return; // Dừng thêm sinh viên mới nếu ID đã tồn tại
        }
    }
         students.add(student);
         System.out.println("Student added successfully.");
    }

    // Method to edit a student's information
    public void editStudent(String id, String newName, double newMarks) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                student.setName(newName);
                student.setMarks(newMarks);
                break;
            }
        }
    }

    // Method to delete a student by ID
    public void deleteStudent(String id) {
        students.removeIf(student -> student.getId().equals(id));
    }

    // Method to sort students by marks using Bubble Sort (ascending)
    public void bubbleSortStudentsByMarks() {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (students.get(j).getMarks() > students.get(j + 1).getMarks()) {
                    // Swap the students at index j and j + 1
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
        System.out.println("Students sorted by marks using Bubble Sort:");
        displayAllStudents();
    }

    // Method to sort students by marks using Quick Sort (ascending)
    public void quickSortStudentsByMarks() {
        quickSort(0, students.size() - 1);
        System.out.println("Students sorted by marks using Quick Sort:");
        displayAllStudents();
    }

    private void quickSort(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);
            quickSort(low, pi - 1);
            quickSort(pi + 1, high);
        }
    }

    private int partition(int low, int high) {
        Student pivot = students.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (students.get(j).getMarks() <= pivot.getMarks()) {
                i++;
                Student temp = students.get(i);
                students.set(i, students.get(j));
                students.set(j, temp);
            }
        }
        Student temp = students.get(i + 1);
        students.set(i + 1, students.get(high));
        students.set(high, temp);
        return i + 1;
    }

    // Method to search for a student by ID
    public Student searchStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) return student;
        }
        return null;
    }

    // Method to display all students
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }
}