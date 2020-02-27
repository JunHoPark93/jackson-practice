package com.jaytechblog.jacksonpractice;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Member5 {
    public int id;
    public String name;

    @JsonCreator
    public Member5(@JsonProperty("id") int id, @JsonProperty("theName") String name) {
        this.id = id;
        this.name = name;
    }
}
