package com.practice.libraryrestservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderRepository implements Repository<Order> {

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

    @Override
    public void add(Order item) {
        this.orderList.add(item);
    }

    @Override
    public void add(Iterable<Order> items) {
        items.forEach((item) -> this.orderList.add(item));
    }

    @Override
    public void update(Iterable<Order> items) {
        this.setOrderList((List<Order>) items);

    }

    @Override
    public Order getById(long id) {
        for (Order order : this.orderList) {
            if (order.getOrderId() == id) {
                return order;
            }
        }
        return null;
    }

}
