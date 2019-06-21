package com.practice.libraryrestservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final RestTemplate restTemplate;

    public OrderService() {
        this.restTemplate = new RestTemplate();
    }


    @Async
    public CompletableFuture<List<Order>> saveNewOrder(Order order, OrderRepository orderRepository) throws InterruptedException {
        orderRepository.add(order);
        Thread.sleep(2000L);

        logger.info("The order with id: {} was saved in the Repository.", order.getOrderId());
        return CompletableFuture.completedFuture(orderRepository.getOrderList());
    }

    public PaymentRepository loadRepository(){

        logger.info("Loading the whole payment repository");
        String url = "https://jsonplaceholder.typicode.com/posts";
        ResponseEntity<List<Payment>> paymentResponse = restTemplate.exchange(url,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Payment>>(){});
        List<Payment> payments = paymentResponse.getBody();

        logger.debug("--> paymentList: {}", payments);

        PaymentRepository paymentRepository = new PaymentRepository(payments);
        logger.info("paymentRepository added.");
        return paymentRepository;
    }

    @Async
    public CompletableFuture<Payment> findById(long id) throws InterruptedException {

        PaymentRepository paymentRepository = loadRepository();

        logger.info("Looking up payment, with id: {}.", id);
        Payment payment = paymentRepository.getById(id);

        // Artificial delay of 1s for demonstration purposes
        Thread.sleep(2000L);

        return CompletableFuture.completedFuture(payment);

    }

    public void processOrder(Order order, OrderRepository orderRepository) throws Exception{

        //start clock to test
        long start = System.nanoTime();

        long paymentId = 3L; //hard coded, might be a random number...(depends on client/order)

        //async calls:
        CompletableFuture<List<Order>> orderList = saveNewOrder(order, orderRepository);
        CompletableFuture<Payment> payment = findById(paymentId); //currently hard coded...

        //join -> wait till allOf async methods are done
        CompletableFuture.allOf(orderList,payment).join();
        order.setPaymentStatus(true);

        logger.info("Elapsed time: {}", (System.nanoTime() - start));
        logger.info("--> payment: {}", payment.get());
        logger.info("--> orderList: {}", orderList.get());
        logger.info("--> paymentStatus: {}", order.isPaymentStatus());
    }





}
