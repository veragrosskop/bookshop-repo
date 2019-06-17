package com.practice.libraryrestservice;

import com.fasterxml.jackson.annotation.JsonView;

public class Book {
// The books have an ISBN number, title and author. Use a hardcoded list of books.

    @JsonView(View.Description.class)
    private long isbn; //in Java JPA @Id should be added to ensure primary key....

    @JsonView(View.Description.class)
    private String title;

    @JsonView(View.Description.class)
    private String author;

    @JsonView(View.DescriptionWithSummary.class)
    private String summary;

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
        // Move to Library ?
        if (isbn == this.isbn) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return title;
    }

//    public boolean searchTitle(String title){
//        if (this.title.contains(title)) {
//            return true;
//        } else {
//            return false;
//        }
//    }
////TO DO : refactor into one function? Interface?
//
//    public boolean searchAuthor(String author){
//        if (this.author.contains(author)) {
//            return true;
//        } else {
//            return false;
//        }
//    }

}
