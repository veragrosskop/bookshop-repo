package com.practice.libraryrestservice;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// look up @Bean annotation
@Component
public class Library {

    /*
    The most popular approach is to implement a Singleton by creating a regular class and making sure it has:
    - A private constructor
    - A static field containing its only instance
    - A static factory method for obtaining the instance
    */

   // private static Library INSTANCE;
    private List<Book> bookArrayList;

//    private static final Object asdf = new Object();

    public Library(){
        //create some sample books
        Book romeoAndJuliet = new Book(9876543210001L, "Romeo and Juliet", "Shakespear",
                "Love story, everybody dies...");
        Book raccontiRomani = new Book(9876543210002L, "Racconti Romani", "Moravia",
                "Short stories centred in post-war Rome.");
        Book newsgames = new Book(9876543210003L, "Newsgames", "Ian Bogost",
                "At the intersection of games and journalism.");
        Book mathilda = new Book(9876543210004L, "Mathilda", "Roald Dahl",
                "The one true bookworm.");
        Book habibi = new Book(9876543210005L, "Habibi", "Craig Thompson",
                "The prettiest graphic novel, with mosaics.");
        Book hetAmusement = new Book(9876543210006L, "Het Amusement", "Brecht Evens",
                "Aquarel drawn graphic novel.");
        Book iPromessiSposi = new Book(9876543210007L, "I Promessi Sposi", "Alessandro Manzoni",
                "Love story, italian style.");
        Book persuasiveGames = new Book(9876543210008L, "Persuasive Games", "Ian Bogost",
                "Contains concepts such as persuasive games and procedural rhetoric.");

        //add books to the default booklist
        ArrayList<Book> defaultBookList = new ArrayList<>();
        defaultBookList.add(romeoAndJuliet);
        defaultBookList.add(raccontiRomani);
        defaultBookList.add(newsgames);
        defaultBookList.add(mathilda);
        defaultBookList.add(habibi);
        defaultBookList.add(hetAmusement);
        defaultBookList.add(iPromessiSposi);
        defaultBookList.add(persuasiveGames);

        //create default library with sample books
        this.bookArrayList = defaultBookList;
    }

    public Library(List<Book> bookArrayList) {
        this.bookArrayList = bookArrayList;
    }

    //    //look up overkill singleton contract + @Bean check what's best
//    public synchronized static Library getInstance() {
//        if (INSTANCE == null) {
//            INSTANCE = new Library();
//        }
//        return INSTANCE;
//    }

    public List<Book> getBookArrayList() {
        /* TO DO: Create a copy of the arraylist so it's well encapsulated? (Read up on thread safety etc */
        return bookArrayList;
    }

    public void setBookArrayList(List<Book> bookArrayList) {
        this.bookArrayList = bookArrayList;
    }

    public Book findIsbn(long isbn){
        for (Book book : this.bookArrayList) {
            if (book.equalIsbn(isbn)) {
                return book;
            }
        }
        return null;
    }

    public List<Book> searchText(String searchString) {

        ArrayList<Book> resultArrayList = new ArrayList<>();

        for (Book book : this.bookArrayList) {
            if (searchHelper(book.getAuthor(), searchString) || searchHelper(book.getTitle(), searchString)) {
                resultArrayList.add(book);
            }
        }
        return resultArrayList;

    }

    private boolean searchHelper (String stringA, String stringB) {

        return stringA.toLowerCase().contains(stringB.toLowerCase());
    }

    public List<Book> requestTitle(String title) {

        ArrayList<Book> resultArrayList = new ArrayList<>();
        for (Book book : this.bookArrayList) {
            if (searchHelper(book.getTitle(), title)) {
                resultArrayList.add(book);
            }
        }
        return resultArrayList;
    }

    public List<Book> requestTitle(String title, List<Book> librarySelection) {

        ArrayList<Book> resultArrayList = new ArrayList<>();

        for (Book book : librarySelection) {
            if (searchHelper(book.getTitle(), title)) {
                resultArrayList.add(book);
            }
        }
        return resultArrayList;
    }

    public List<Book> requestAuthor(String author) {

        ArrayList<Book> resultArrayList = new ArrayList<>();


        for (Book book : this.bookArrayList) {
            if (searchHelper(book.getAuthor(), author)) {
                resultArrayList.add(book);
            }
        }
        return resultArrayList;
    }


}
