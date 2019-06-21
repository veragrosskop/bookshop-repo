package com.practice.libraryrestservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class OrderRepository {

    private List<Order> orderList;

    private static final Logger logger = LoggerFactory.getLogger(OrderRepository.class);

    public OrderRepository() {
        this.orderList = new ArrayList<Order>();
    }

    public List<Order> getOrderList() {
        return this.orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    @Async
    public CompletableFuture<List<Order>> saveNewOrder(Order order) throws InterruptedException {
        this.orderList.add(order);
        Thread.sleep(2000L);

        logger.info("The order with id: {} was saved in the Repository.", order.getOrderId());
        return CompletableFuture.completedFuture(this.orderList);
    }


}
