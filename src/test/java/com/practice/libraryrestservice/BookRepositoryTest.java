package com.practice.libraryrestservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class BookRepositoryTest {

    private final long nonExistingIsbn = 0L;

    private Book testBook1 = new Book(1L, "tit1", "aut1", "sum1");
    private Book testBook2 = new Book(2L, "tit2", "aut2", "sum2");
    private Book testBook3 = new Book(3L, "tit3", "aut3", "sum3");
    private Book testBook4 = new Book(4L, "tit4", "aut4", "sum4");

    private Book[] noResultsArray = new Book[0];
    private Book[] bookArray = {testBook1, testBook2, testBook3, testBook4};

    //given for all tests
    @Autowired
    private BookRepository testBookRepository = new BookRepository(Arrays.asList(bookArray));

    @Test
    public void testFindIsbn(){
        //given
        long nonExistingIsbn = 0L;
        long existingIsbn = 1L;

        //when
        Book notFound = testBookRepository.getById(nonExistingIsbn);
        Book found = testBookRepository.getById(existingIsbn);

        //then
        assertNull(notFound);
        assertEquals(testBook1, found);
    }

    @Test
    public void testRequestTitle() {

        //short or as above?
        assertEquals(testBookRepository.getBookArrayList(), testBookRepository.requestTitle("tit"));
        assertArrayEquals(noResultsArray, testBookRepository.requestTitle("no matches title").toArray());
    }

    @Test
    public void testRequestAuthor() {
        assertEquals(testBookRepository.getBookArrayList(), testBookRepository.requestAuthor("aut"));
        assertArrayEquals(noResultsArray, testBookRepository.requestAuthor("no matches author").toArray());
    }
}