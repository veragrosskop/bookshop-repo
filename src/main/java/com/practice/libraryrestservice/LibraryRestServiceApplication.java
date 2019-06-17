package com.practice.libraryrestservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
public class LibraryRestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryRestServiceApplication.class, args);
    }

    @Bean
    //needs to be named taskExecutor so Spring can AutoConfigure
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //comparable to Executors.newSingleThreadExecutor()

        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.initialize();

        return executor;
    }
}
