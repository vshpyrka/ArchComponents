package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {

    @JsonProperty("name")
    public String name;

    @JsonProperty("order")
    public int order;
}
