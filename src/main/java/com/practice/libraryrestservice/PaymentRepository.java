package com.practice.libraryrestservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PaymentRepository implements Repository<Payment> {

    private final Logger logger = LoggerFactory.getLogger(PaymentRepository.class);
    private List<Payment> paymentList;

    public PaymentRepository(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    public PaymentRepository(){
        this.paymentList = new ArrayList<>();
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    @Override
    public void add(Payment item) {
        this.paymentList.add(item);
    }

    @Override
    public void add(Iterable<Payment> items) {
        items.forEach((item) -> this.paymentList.add(item));
    }

    @Override
    public void update(Iterable<Payment> items) {
        this.setPaymentList((List<Payment>) items);
    }

    @Override
    public Payment getById(long id) {
        for (Payment payment : this.paymentList) {
            if (payment.getId() == id) {
                return payment;
            }
        }
        return null;
    }

}
