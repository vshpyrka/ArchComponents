package com.inveritasoft.archcomponents.presentation.main.adapter;

public class BookModel {

    private String name;
    private int order;

    public BookModel(String name, int order) {
        this.name = name;
        this.order = order;
    }

    public BookModel(BookModel bookModel) {
        this.name = bookModel.getName();
        this.order = bookModel.getOrder();
    }

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(final int order) {
        this.order = order;
    }
}
