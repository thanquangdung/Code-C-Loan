/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ASMtest;

import java.util.Scanner;

/**
 *
 * @author Hi
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagement management = new StudentManagement();

        while (true) {
            System.out.println("\nStudent Management System:");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Sort Students by Marks");
            System.out.println("5. Search Student by ID");
            System.out.println("6. Display All Students");
            System.out.println("7. Exit");
            System.out.print("Please choose an option: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine()); // Read and convert input to an integer
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a number between 1 and 7.");
                continue;
            }

            switch (choice) {
                case 1: // Add student
                    System.out.print("How many students do you want to add? ");
                    int numStudents;
                    try {
                        numStudents = Integer.parseInt(scanner.nextLine());
                        if (numStudents <= 0) {
                            System.out.println("The number of students must be a positive integer. Please try again.");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("The number of students must be a positive integer. Please try again.");
                        break;
                    }

                    for (int i = 0; i < numStudents; i++) {
                        System.out.println("Enter information for student " + (i + 1) + ":");

                        String id;
                        while (true) {
                            System.out.print("Enter ID: ");
                            id = scanner.nextLine();
                            if (id.matches("\\d+")) {
                                break;
                            } else {
                                System.out.println("Error: ID can only contain digits. Please enter again.");
                            }
                        }

                        String name;
                        while (true) {
                            System.out.print("Enter name: ");
                            name = scanner.nextLine();
                            if (name.matches("[a-zA-Z\\s]+")) {
                                break;
                            } else {
                                System.out.println("Error: Name can only contain letters and spaces. Please enter again.");
                            }
                        }

                        double marks;
                        while (true) {
                            System.out.print("Enter marks: ");
                            String marksInput = scanner.nextLine();
                            try {
                                marks = Double.parseDouble(marksInput);
                                if (marks < 0 || marks > 10) {
                                    System.out.println("Error: Marks must be between 0 and 10. Please enter again.");
                                    continue;
                                }
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Error: Marks must be a number. Please enter again.");
                            }
                        }

                        management.addStudent(new Student(id, name, marks));
                        System.out.println("Student added successfully.");
                    }
                    System.out.println("All students have been added.");
                    break;
                case 2: // Edit student
                    String id;
                    while (true) {
                        System.out.print("Enter the ID of the student to edit: ");
                        id = scanner.nextLine();
                        if (id.matches("\\d+")) {
                            break;
                        } else {
                            System.out.println("Error: ID can only contain digits. Please enter again.");
                        }
                    }

                    System.out.print("Enter new name: ");
                    String name = scanner.nextLine();
                    if (!name.matches("[a-zA-Z\\s]+")) {
                        System.out.println("Error: Name can only contain letters and spaces. Please try again.");
                        break;
                    }

                    double marks;
                    while (true) {
                        System.out.print("Enter new marks: ");
                        String marksInput = scanner.nextLine();
                        try {
                            marks = Double.parseDouble(marksInput);
                            if (marks < 0 || marks > 10) {
                                System.out.println("Error: Marks must be between 0 and 10. Please enter again.");
                                continue;
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Marks must be a number. Please enter again.");
                        }
                    }

                    Student existingStudent = management.searchStudentById(id);
                    if (existingStudent != null) {
                        management.editStudent(id, name, marks);
                        System.out.println("Student edited successfully.");
                    } else {
                        System.out.println("Student with the given ID not found.");
                    }
                    break;
                case 3: // Delete student
                    while (true) {
                        System.out.print("Enter the ID of the student to delete: ");
                        id = scanner.nextLine();
                        if (id.matches("\\d+")) {
                            break;
                        } else {
                            System.out.println("Error: ID can only contain digits. Please enter again.");
                        }
                    }

                    existingStudent = management.searchStudentById(id);
                    if (existingStudent != null) {
                        management.deleteStudent(id);
                        System.out.println("Student deleted successfully.");
                    } else {
                        System.out.println("Student with the given ID not found.");
                    }
                    break;
                case 4: // Sort students by marks
                    System.out.println("Choose sorting method:");
                    System.out.println("1. Bubble Sort");
                    System.out.println("2. Quick Sort");
                    System.out.print("Please choose an option: ");
                    int sortChoice;
                    try {
                        sortChoice = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input, please enter 1 or 2.");
                        break;
                    }

                    if (sortChoice == 1) {
                        management.bubbleSortStudentsByMarks();
                    } else if (sortChoice == 2) {
                        management.quickSortStudentsByMarks();
                    } else {
                        System.out.println("Invalid choice, please enter 1 or 2.");
                    }
                    break;
                case 5: // Search student by ID
                    while (true) {
                        System.out.print("Enter the ID of the student to search: ");
                        id = scanner.nextLine();
                        if (id.matches("\\d+")) {
                            break;
                        } else {
                            System.out.println("Error: ID can only contain digits. Please enter again.");
                        }
                    }

                    Student student = management.searchStudentById(id);
                    if (student != null) {
                        System.out.println("Student found: " + student);
                    } else {
                        System.out.println("Student with the given ID not found.");
                    }
                    break;
                case 6: // Display all students
                    management.displayAllStudents();
                    break;
                case 7: // Exit
                    System.out.println("Exiting program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}