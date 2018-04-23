package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {

    @JsonProperty("name")
    public String name;

    @JsonProperty("category_id")
    public long categoryId;

    @JsonProperty("order")
    public int order;
}
