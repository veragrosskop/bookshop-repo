package com.practice.libraryrestservice;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Payment {

    private int userId;
    private long id;
    private String title;
    private String body;

    @JsonCreator
    public Payment(@JsonProperty("userId") int userId,
                   @JsonProperty("id") long id,
                   @JsonProperty("title") String title,
                   @JsonProperty("body") String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }


    public int getUserId() {
        return userId;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }


    public String toString() {
        return "" + userId + ","+ id + "," + title;
    }
}
