package com.practice.libraryrestservice;

import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
public class LibraryController {

    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;
    private final OrderService orderService;

    public LibraryController(final BookRepository bookRepository, final OrderRepository orderRepository,
                             final OrderService orderService) {
        this.bookRepository = bookRepository;
        this.orderRepository = orderRepository;
        this.orderService = orderService;
    }

    @JsonView(View.Description.class)  //show only descriptive attributes
    @GetMapping(path="/books")
    public List<Book> getBooksFromLibrary() {
        return bookRepository.getBookArrayList();
    }

    @JsonView(View.DescriptionWithSummary.class)  //show summary as well as descriptive attributes
    @GetMapping (value = "/books/{isbn}")
    public Book getBooksByIsbn(@PathVariable("isbn") long isbn) {
        return bookRepository.getById(isbn);
    }


    @JsonView(View.Description.class)  //show only descriptive attributes
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<Book> getBooksFromSearch(@RequestParam("searchString") String searchString) {
        return bookRepository.searchText(searchString);
    }

    @JsonView(View.Description.class) //show only descriptive attributes
    @GetMapping( "/search2")
    public List<Book> getBooksFromSearch2(@RequestParam Map<String, String> requestParams) {

        String author = requestParams.get("author");
        String title = requestParams.get("title");

        List<Book> resultList = new ArrayList<>();

        if (author != null) {
            resultList = bookRepository.requestAuthor(author);

        } else {
            resultList = bookRepository.getBookArrayList();
        }

        if (title != null) {
            resultList = bookRepository.requestTitle(title, resultList);
        }
        return resultList;
    }


    @PostMapping("/order")
    public void postOrder(@Valid @RequestBody Order customerOrder) throws Exception {
        orderService.processOrder(customerOrder, orderRepository);
    }

}
