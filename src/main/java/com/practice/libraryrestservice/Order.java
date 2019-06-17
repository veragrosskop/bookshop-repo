package com.practice.libraryrestservice;

import javax.validation.constraints.NotNull;
import java.util.List;

public class Order {

    private long orderId;

    @NotNull
    private long customerId;

    @NotNull
    private List<Book> bookList;

    private boolean paymentStatus;

    public Order(long orderId, long customerId, List<Book> bookList) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.bookList = bookList;
        this.paymentStatus = false;
    }

    public long getOrderId() {
        return orderId;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String toString() {
        return "" + orderId + "," + customerId + "," + bookList + "," + paymentStatus;
    }
}
