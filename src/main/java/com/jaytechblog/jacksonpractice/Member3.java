package com.jaytechblog.jacksonpractice;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "name", "id" })
public class Member3 {
    public int id;
    public String name;

    public Member3(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
