package com.jaytechblog.jacksonpractice;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;

public class Event2 {
    public String name;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    public Date eventDate;
}
