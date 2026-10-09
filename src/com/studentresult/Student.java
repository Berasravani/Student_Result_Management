package com.studentresult;

public class Student extends User {

    private String rollNumber;
    private String department;

    // Five subject marks
    private int[] marks;

    // Constructor
    public Student(int id,
                   String name,
                   String rollNumber,
                   String department) {

        super(id, name);

        this.rollNumber = rollNumber;
        this.department = department;

        // Initially marks are not entered
        this.marks = null;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getDepartment() {
        return department;
    }

    // Check whether marks are entered
    public boolean hasMarks() {

        return marks != null;
    }

    // Set marks
    public void setMarks(int[] marks) {

        this.marks = marks.clone();
    }

    // Get marks
    public int[] getMarks() {

        if (marks == null) {
            return null;
        }

        return marks.clone();
    }

    // Calculate total
    public int getTotal() {

        int total = 0;

        for (int mark : marks) {
            total += mark;
        }

        return total;
    }

    // Calculate average
    public double getAverage() {

        return getTotal() / 5.0;
    }

    // Check pass/fail
    public boolean isPass() {

        for (int mark : marks) {

            if (mark < 40) {
                return false;
            }
        }

        return true;
    }

    // Student profile
    @Override
    public void showProfile() {

        System.out.println("Student ID   : " + getId());
        System.out.println("Name         : " + getName());
        System.out.println("Roll Number  : " + rollNumber);
        System.out.println("Department   : " + department);
    }
}