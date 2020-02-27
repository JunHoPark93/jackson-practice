package com.jaytechblog.jacksonpractice;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Member10 {
    public int id;
    public String name;

    public Member10(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
