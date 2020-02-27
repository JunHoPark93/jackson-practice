package com.jaytechblog.jacksonpractice;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import java.util.HashMap;
import java.util.Map;

public class Member {
    public String name;
    private Map<String, String> properties = new HashMap<>();

    public Member(String name) {
        this.name = name;
    }

    @JsonAnyGetter
    public Map<String, String> getProperties() {
        return properties;
    }

    public void add(String attr, String val) {
        this.properties.put(attr, val);
    }
}