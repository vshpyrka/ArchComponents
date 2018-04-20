package com.inveritasoft.archcomponents.presentation.main.adapter;

public class BookModel extends BaseView {

    public BookModel(String type, String name, int order) {
        setOrder(order);
        setType(type);
        setName(name);
    }

    public BookModel(BaseView bookModel) {
        setOrder(bookModel.getOrder());
        setType(bookModel.getType());
        setName(bookModel.getName());
    }
}
