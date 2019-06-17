package com.practice.libraryrestservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PaymentRepository {
    private final Logger logger = LoggerFactory.getLogger(PaymentRepository.class);


    private List<Payment> paymentList;

    public PaymentRepository(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public Payment requestPayment(int userId, int id) {

        Payment foundPayment = null;

        for (Payment payment : this.paymentList) {
            logger.debug("checking payment {}.", payment);

            if (payment.equalIds(userId, id)) {
                logger.info("payment {}, {} was found", userId, id);
                foundPayment = payment;
            }
        }
        return foundPayment;
    }
}
