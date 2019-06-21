package com.practice.libraryrestservice;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
public class PaymentServiceTest {

    @Autowired
    private OrderService orderService;

    @MockBean
    private PaymentRepository paymentRepository;

    @Before
    public void setUp() {
        Payment paymentZero = new Payment(0, 0L, "0", "0");

        Mockito.when(paymentRepository.getById(paymentZero.getId()))
                .thenReturn(paymentZero);
    }


//    @Test
//    public void whenValidIds_thenPaymentShouldBeFound() {
//
//        int userId = 0;
//        int id = 0;
//        Payment found = paymentService.findById(userId, id);
//
//        assertThat(found.getUserId())
//                .isEqualTo(userId);
//    }
//


}
