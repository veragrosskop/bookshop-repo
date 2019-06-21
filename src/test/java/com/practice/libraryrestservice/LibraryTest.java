package com.practice.libraryrestservice;

import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.*;

public class LibraryTest {

    private Book testBook1 = new Book(1L, "tit1", "aut1", "sum1");
    private Book testBook2 = new Book(2L, "tit2", "aut2", "sum2");
    private Book testBook3 = new Book(3L, "tit3", "aut3", "sum3");
    private Book testBook4 = new Book(4L, "tit4", "aut4", "sum4");

    private Book[] noResultsArray = new Book[0];
    private Book[] bookArray = {testBook1, testBook2, testBook3, testBook4};
    private Library testLibrary = new Library(Arrays.asList(bookArray));

    @Test
    public void testFindIsbn(){
        assertNull(testLibrary.findIsbn(0L));
        assertEquals(testBook1, testLibrary.findIsbn(testBook1.getIsbn()));
    }

    @Test
    public void testRequestTitle() {
        assertEquals(testLibrary.getBookArrayList(), testLibrary.requestTitle("tit"));
        assertArrayEquals(noResultsArray, testLibrary.requestTitle("no matches title").toArray());
    }

    @Test
    public void testRequestAuthor() {
        assertEquals(testLibrary.getBookArrayList(), testLibrary.requestAuthor("aut"));
        assertArrayEquals(noResultsArray, testLibrary.requestAuthor("no matches author").toArray());
    }
}