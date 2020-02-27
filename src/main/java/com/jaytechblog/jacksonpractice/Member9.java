package com.jaytechblog.jacksonpractice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"id"})
public class Member9 {
    public int id;
    public String name;

    public Member9(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
