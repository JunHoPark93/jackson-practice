package com.jaytechblog.jacksonpractice;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Member8 {
    @JsonAlias({"name", "his_name", "her_name"})
    public String name;
    public String hobby;
}
