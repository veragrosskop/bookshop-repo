package com.practice.libraryrestservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController

public class LibraryController {

    private final Library library;


//error because Library is not a @Bean
    public LibraryController(Library library) {
        this.library = library;
    }

    @GetMapping(path="/books")
    public ArrayList<Book> getBooksFromLibrary() {

        return library.getBookArrayList();
    }

    @GetMapping (value = "/books/{isbn}")
    @ResponseBody
    public Book getBooksOnIsbn(@PathVariable("isbn") long isbn) {

        return Library.getInstance().findIsbn(isbn);
    }
}
