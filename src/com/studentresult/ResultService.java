package com.studentresult;

import java.util.ArrayList;
import java.util.HashSet;

public class ResultService {

    private ArrayList<Student> students;

    private HashSet<String> rollNumbers;

    private int nextStudentId;

    private String[] subjects = {
            "Java",
            "Mathematics",
            "English",
            "DBMS",
            "Computer Networks"
    };

    // Constructor
    public ResultService() {

        students = new ArrayList<>();

        rollNumbers = new HashSet<>();

        nextStudentId = 1;
    }

    // Register student
    public Student registerStudent(
            String name,
            String rollNumber,
            String department) {

        rollNumber =
                rollNumber.trim().toUpperCase();

        // Check duplicate roll number
        if (rollNumbers.contains(rollNumber)) {

            System.out.println(
                    "Roll number already exists."
            );

            return null;
        }

        Student student =
                new Student(
                        nextStudentId,
                        name,
                        rollNumber,
                        department
                );

        students.add(student);

        rollNumbers.add(rollNumber);

        nextStudentId++;

        System.out.println(
                "Student " +
                student.getId() +
                " saved. Roll number: " +
                rollNumber
        );

        return student;
    }

    // Find student by ID
    public Student findStudent(int id) {

        for (Student student : students) {

            if (student.getId() == id) {

                return student;
            }
        }

        return null;
    }

    // Enter marks
    public boolean enterMarks(
            int studentId,
            int[] marks) {

        Student student =
                findStudent(studentId);

        if (student == null) {

            System.out.println(
                    "Student not found."
            );

            return false;
        }

        // Validate exactly five marks
        if (marks.length != 5) {

            System.out.println(
                    "Exactly five marks are required."
            );

            return false;
        }

        // Validate marks
        for (int mark : marks) {

            if (mark < 0 || mark > 100) {

                System.out.println(
                        "Marks must be between 0 and 100."
                );

                return false;
            }
        }

        // Save only after all marks are valid
        student.setMarks(marks);

        System.out.println(
                "Marks saved."
        );

        return true;
    }

    // Show result
    public void showResult(int studentId) {

        Student student =
                findStudent(studentId);

        if (student == null) {

            System.out.println(
                    "Student not found."
            );

            return;
        }

        if (!student.hasMarks()) {

            System.out.println(
                    "Marks not entered."
            );

            return;
        }

        int[] marks =
                student.getMarks();

        System.out.println(
                "\n========== RESULT =========="
        );

        System.out.println(
                "Student ID : " +
                student.getId()
        );

        System.out.println(
                "Name       : " +
                student.getName()
        );

        System.out.println(
                "Roll Number: " +
                student.getRollNumber()
        );

        System.out.println(
                "Department : " +
                student.getDepartment()
        );

        System.out.println(
                "----------------------------"
        );

        for (int i = 0; i < subjects.length; i++) {

            System.out.println(
                    subjects[i] +
                    " : " +
                    marks[i]
            );
        }

        System.out.println(
                "----------------------------"
        );

        System.out.println(
                "Total      : " +
                student.getTotal() +
                " / 500"
        );

        System.out.printf(
                "Average    : %.2f%n",
                student.getAverage()
        );

        if (student.isPass()) {

            System.out.println(
                    "Result     : PASS"
            );

        } else {

            System.out.println(
                    "Result     : FAIL"
            );
        }

        System.out.println(
                "============================"
        );
    }

    // View all students
    public void showStudents() {

        if (students.isEmpty()) {

            System.out.println(
                    "No records found."
            );

            return;
        }

        for (Student student : students) {

            System.out.println(
                    "\n----------------------------"
            );

            student.showProfile();
        }
    }

    // Show class report
    public void showReport() {

        int totalStudents =
                students.size();

        int studentsWithMarks = 0;

        int awaitingMarks = 0;

        int passCount = 0;

        int failCount = 0;

        double highestAverage = 0;

        for (Student student : students) {

            if (!student.hasMarks()) {

                awaitingMarks++;

                continue;
            }

            studentsWithMarks++;

            if (student.isPass()) {

                passCount++;

            } else {

                failCount++;
            }

            if (student.getAverage()
                    > highestAverage) {

                highestAverage =
                        student.getAverage();
            }
        }

        System.out.println(
                "\n========== CLASS REPORT =========="
        );

        System.out.println(
                "Total Students       : " +
                totalStudents
        );

        System.out.println(
                "Students with Marks  : " +
                studentsWithMarks
        );

        System.out.println(
                "Awaiting Marks       : " +
                awaitingMarks
        );

        System.out.println(
                "PASS                 : " +
                passCount
        );

        System.out.println(
                "FAIL                 : " +
                failCount
        );

        if (studentsWithMarks > 0) {

            System.out.printf(
                    "Highest Average      : %.2f%n",
                    highestAverage
            );
        }

        System.out.println(
                "==================================="
        );
    }

    // Get students
    public ArrayList<Student> getStudents() {

        return students;
    }
}