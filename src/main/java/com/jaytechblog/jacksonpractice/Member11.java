package com.jaytechblog.jacksonpractice;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Member11 {
    private int id;
    private String name;

    public Member11(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
