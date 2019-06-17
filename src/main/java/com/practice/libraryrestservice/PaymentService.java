package com.practice.libraryrestservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    private final RestTemplate restTemplate;

    public PaymentService() {
        this.restTemplate = new RestTemplate();
    }

    public PaymentRepository loadRepository(){

        logger.info("Loading the whole payment repository");
        String url = "https://jsonplaceholder.typicode.com/posts";
        ResponseEntity<List<Payment>> paymentResponse = restTemplate.exchange(url,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Payment>>(){});
        List<Payment> payments = paymentResponse.getBody();

        PaymentRepository paymentRepository = new PaymentRepository(payments);
        logger.info("paymentRepository added.");
        return paymentRepository;

    }

    @Async
    public CompletableFuture<Payment> findPayment(int userId, int id) throws InterruptedException {

        PaymentRepository paymentRepository = loadRepository();
        logger.debug("--> paymentList: {}", paymentRepository.getPaymentList());

        logger.info("Looking up payment, with userId: {}, and id: {}.", userId, id);
        Payment payment = paymentRepository.requestPayment(userId, id);

        // Artificial delay of 1s for demonstration purposes
        Thread.sleep(1000L);

        return CompletableFuture.completedFuture(payment);

    }




}
