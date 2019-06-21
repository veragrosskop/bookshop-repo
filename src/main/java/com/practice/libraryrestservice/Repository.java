package com.practice.libraryrestservice;

import java.util.List;

public interface Repository<T> {
    void add(T item);

    void add(Iterable<T> items);

    void update(Iterable<T> items);

//    void update(String url);

//    void remove(T item);

//    List<T> query(T item);

    T getById(long id);

}