package com.inveritasoft.archcomponents.presentation.main.adapter;

public class CategoryModel extends BaseView {

    public CategoryModel(String type, String name, int order) {
        setOrder(order);
        setType(type);
        setName(name);
    }
}
