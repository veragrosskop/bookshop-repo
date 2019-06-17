package com.practice.libraryrestservice;

import java.util.List;

public interface PrintBooks {

    public default String printBookList(List<Book> bookList){

        StringBuilder names = new StringBuilder("books ordered:");

        for(Book book:bookList){
            names.append("\n"+ book.getTitle());
        }

        return names.toString();
    }
}
