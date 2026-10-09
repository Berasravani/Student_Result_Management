package com.studentresult;

import java.util.Scanner;

public class Main {

    static Scanner scanner =
            new Scanner(System.in);

    static ResultService service =
            new ResultService();

    static Teacher teacher =
            new Teacher(0, "Teacher");

    public static void main(String[] args) {

        while (true) {

            System.out.println(
                    "\n=============================="
            );

            System.out.println(
                    " STUDENT RESULT MANAGEMENT"
            );

            System.out.println(
                    "=============================="
            );

            System.out.println(
                    "1. Teacher Menu"
            );

            System.out.println(
                    "2. Student Menu"
            );

            System.out.println(
                    "0. Exit"
            );

            System.out.print(
                    "Enter your choice: "
            );

            String choice =
                    scanner.nextLine();

            switch (choice) {

                case "1":

                    showTeacherMenu();

                    break;

                case "2":

                    showStudentMenu();

                    break;

                case "0":

                    System.out.println(
                            "Thank you!"
                    );

                    scanner.close();

                    return;

                default:

                    System.out.println(
                            "Invalid choice."
                    );
            }
        }
    }

    // ============================
    // TEACHER MENU
    // ============================

    public static void showTeacherMenu() {

        while (true) {

            System.out.println(
                    "\n--------- TEACHER MENU ---------"
            );

            System.out.println(
                    "1. Register Student"
            );

            System.out.println(
                    "2. View Students"
            );

            System.out.println(
                    "3. Enter / Update Marks"
            );

            System.out.println(
                    "4. View All Results"
            );

            System.out.println(
                    "5. View Report"
            );

            System.out.println(
                    "0. Back"
            );

            System.out.print(
                    "Enter choice: "
            );

            String choice =
                    scanner.nextLine();

            switch (choice) {

                case "1":

                    registerStudent();

                    break;

                case "2":

                    service.showStudents();

                    break;

                case "3":

                    enterMarks();

                    break;

                case "4":

                    viewAllResults();

                    break;

                case "5":

                    service.showReport();

                    break;

                case "0":

                    return;

                default:

                    System.out.println(
                            "Invalid choice."
                    );
            }
        }
    }

    // ============================
    // REGISTER STUDENT
    // ============================

    public static void registerStudent() {

        System.out.print(
                "Enter student name: "
        );

        String name =
                scanner.nextLine().trim();

        System.out.print(
                "Enter roll number: "
        );

        String rollNumber =
                scanner.nextLine().trim();

        System.out.print(
                "Enter department: "
        );

        String department =
                scanner.nextLine().trim();

        if (name.isEmpty()
                || rollNumber.isEmpty()
                || department.isEmpty()) {

            System.out.println(
                    "All fields are required."
            );

            return;
        }

        service.registerStudent(
                name,
                rollNumber,
                department
        );
    }

    // ============================
    // ENTER MARKS
    // ============================

    public static void enterMarks() {

        System.out.print(
                "Enter student ID: "
        );

        int id =
                readInteger();

        int[] marks =
                new int[5];

        String[] subjects = {

                "Java",
                "Mathematics",
                "English",
                "DBMS",
                "Computer Networks"
        };

        for (int i = 0;
             i < subjects.length;
             i++) {

            while (true) {

                System.out.print(
                        subjects[i] +
                        ": "
                );

                int mark =
                        readInteger();

                if (mark >= 0
                        && mark <= 100) {

                    marks[i] = mark;

                    break;

                } else {

                    System.out.println(
                            "Enter marks between 0 and 100."
                    );
                }
            }
        }

        service.enterMarks(
                id,
                marks
        );
    }

    // ============================
    // VIEW ALL RESULTS
    // ============================

    public static void viewAllResults() {

        if (service.getStudents().isEmpty()) {

            System.out.println(
                    "No records found."
            );

            return;
        }

        for (Student student :
                service.getStudents()) {

            service.showResult(
                    student.getId()
            );
        }
    }

    // ============================
    // STUDENT MENU
    // ============================

    public static void showStudentMenu() {

        System.out.print(
                "Enter student ID: "
        );

        int id =
                readInteger();

        Student student =
                service.findStudent(id);

        if (student == null) {

            System.out.println(
                    "Student not found."
            );

            return;
        }

        while (true) {

            System.out.println(
                    "\n--------- STUDENT MENU ---------"
            );

            System.out.println(
                    "1. View My Profile"
            );

            System.out.println(
                    "2. View My Result"
            );

            System.out.println(
                    "0. Back"
            );

            System.out.print(
                    "Enter choice: "
            );

            String choice =
                    scanner.nextLine();

            switch (choice) {

                case "1":

                    student.showProfile();

                    break;

                case "2":

                    service.showResult(id);

                    break;

                case "0":

                    return;

                default:

                    System.out.println(
                            "Invalid choice."
                    );
            }
        }
    }

    // ============================
    // READ INTEGER
    // ============================

    public static int readInteger() {

        while (true) {

            String input =
                    scanner.nextLine();

            try {

                return Integer.parseInt(
                        input
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid number."
                );

                System.out.print(
                        "Try again: "
                );
            }
        }
    }
}