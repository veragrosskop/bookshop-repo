package com.practice.libraryrestservice;

public class Payment {

    private int userId;
    private int id;
    private String title;
    private String body;

    public Payment(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public Payment() {
        super();
    }

    public boolean equalIds(int userId, int id) {
        return this.userId == userId && this.id==id;
    }

    public String toString() {
        return "" + userId + ","+ id + "," + title;
    }
}
