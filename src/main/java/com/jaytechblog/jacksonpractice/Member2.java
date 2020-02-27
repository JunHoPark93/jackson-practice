package com.jaytechblog.jacksonpractice;

import com.fasterxml.jackson.annotation.JsonGetter;

public class Member2 {
    public int id;
    private String name;

    public Member2(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @JsonGetter("name")
    public String getTheName() {
        return name;
    }
}
