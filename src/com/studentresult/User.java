package com.studentresult;

public abstract class User {

    private int id;
    private String name;

    // Constructor
    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Abstract method
    public abstract void showProfile();
}