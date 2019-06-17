package com.practice.libraryrestservice;

import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class LibraryController {

    private final Library library;
    private final OrderRepository orderRepository;
    private final PaymentService paymentService;

    private final Logger logger = LoggerFactory.getLogger(LibraryController.class);

    public LibraryController(final Library library, final OrderRepository orderRepository,
                             final PaymentService paymentService) {
        this.library = library;
        this.orderRepository = orderRepository;
        this.paymentService = paymentService;
    }

    @JsonView(View.Description.class)  //show only descriptive attributes
    @GetMapping(path="/books")
    public List<Book> getBooksFromLibrary() {

        return library.getBookArrayList();
    }

    @JsonView(View.DescriptionWithSummary.class)  //show summary as well as descriptive attributes
    @GetMapping (value = "/books/{isbn}")
    public Book getBooksByIsbn(@PathVariable("isbn") long isbn) {

        return library.findIsbn(isbn);
    }


    @JsonView(View.Description.class)  //show only descriptive attributes
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<Book> getBooksByAuthorRequestParam(@RequestParam("searchString") String searchString) {

        return library.searchText(searchString);
    }

    @JsonView(View.Description.class) //show only descriptive attributes
    @GetMapping( "/search2")
    public List<Book> getBooksFromSearch(@RequestParam Map<String, String> requestParams) {

        String author = requestParams.get("author");
        String title = requestParams.get("title");

        List<Book> resultList = new ArrayList<>();

        if (author != null) {
            resultList = library.requestAuthor(author);

        } else {
            resultList = library.getBookArrayList();
        }

        if (title != null) {
            resultList = library.requestTitle(title, resultList);
        }
        return resultList;
    }

    @PostMapping("/orderinitial")
    public List<Order> makeBookOrder(@Valid @RequestBody Order customerOrder) {

        orderRepository.saveNewOrder(customerOrder);
        PaymentRepository paymentRepository = paymentService.loadRepository();

        paymentRepository.getPaymentList().forEach(System.out::println);

        return orderRepository.getOrderList();
    }

    @Async
    @PostMapping("/order")
    public void verifyPayment(@Valid @RequestBody Order customerOrder)
            throws InterruptedException, ExecutionException {

        //start clock to test
        long start = System.currentTimeMillis();


        //async calls:
        CompletableFuture<List<Order>> orderList = orderRepository.saveNewOrder(customerOrder);
        CompletableFuture<Payment> payment = paymentService.findPayment(1, 1); //currently hard coded...

        //join -> wait till allOf async methods are done
        CompletableFuture.allOf(orderList,payment).join();
        customerOrder.setPaymentStatus(true);

        logger.info("Elapsed time: {}", (System.currentTimeMillis() - start));
        logger.info("--> payment: {}", payment.get());
        logger.info("--> orderList: {}", orderList.get());
        logger.info("--> paymentStatus: {}", customerOrder.isPaymentStatus());

    }
}
