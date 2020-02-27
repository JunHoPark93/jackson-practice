package com.jaytechblog.jacksonpractice;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "member")
public class Member4 {
    private int id;
    public String name;

    public Member4(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
