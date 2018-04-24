package com.inveritasoft.archcomponents.api.model;

import java.util.List;

public class BookList {

    private List<Book> books;

    public BookList(final List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(final List<Book> books) {
        this.books = books;
    }
}
