package com.practice.libraryrestservice;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Payment {

    private int userId;
    private int id;
    private String title;
    private String body;

    @JsonCreator
    public Payment(@JsonProperty("userId") int userId,
                   @JsonProperty("id") int id,
                   @JsonProperty("title") String title,
                   @JsonProperty("body") String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public boolean equalIds(int userId, int id) {
        return this.userId == userId && this.id==id;
    }

    public String toString() {
        return "" + userId + ","+ id + "," + title;
    }
}
