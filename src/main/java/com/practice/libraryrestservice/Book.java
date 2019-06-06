package com.practice.libraryrestservice;

public class Book {
// The books have an ISBN number, title and author. Use a hardcoded list of books.

    private long isbn; //in Java JPA @Id should be added to ensure primary key....
    private String title;
    private String author;
    private String summary;

    public Book(long isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    public Book(long isbn, String title, String author, String summary) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.summary = summary;
    }


    public long getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public boolean equalIsbn(long isbn){
        if (isbn == this.isbn) {
            return true;
        } else {
            return false;
        }
    }
}
