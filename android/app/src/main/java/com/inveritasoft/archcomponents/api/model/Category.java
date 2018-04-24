package com.inveritasoft.archcomponents.api.model;

import java.util.List;

public class Category {

    private List<Book> books = null;
    private Integer categoryId;
    private String categoryName;

    public Category() {
    }

    public Category(List<Book> books, Integer categoryId, String categoryName) {
        super();
        this.books = books;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
