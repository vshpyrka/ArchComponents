package com.inveritasoft.archcomponents.api.model;

public class Book {

    private String name;
    private Integer order;
    private Integer categoryId;


    public Book() {
    }

    public Book(String name, Integer order, Integer categoryId) {
        super();
        this.name = name;
        this.order = order;
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
