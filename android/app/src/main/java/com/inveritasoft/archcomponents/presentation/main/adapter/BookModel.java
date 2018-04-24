package com.inveritasoft.archcomponents.presentation.main.adapter;

public class BookModel extends BaseView {

    public BookModel(String type, String name, int order, int categoryId) {
        setOrder(order);
        setType(type);
        setName(name);
        setCategoryId(categoryId);
    }

    public BookModel(BaseView bookModel) {
        setOrder(bookModel.getOrder());
        setType(bookModel.getType());
        setName(bookModel.getName());
        setCategoryId(bookModel.getCategoryId());
    }
}
