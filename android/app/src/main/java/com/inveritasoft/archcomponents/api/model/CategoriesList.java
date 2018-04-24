package com.inveritasoft.archcomponents.api.model;

import java.util.List;

/**
 * Created by Oleksandr Kryvoruchko on 24.04.2018.
 */
public class CategoriesList {

    private List<Category> categories = null;

    /**
     * No args constructor for use in serialization
     */
    public CategoriesList() {
    }

    /**
     * @param categories
     */
    public CategoriesList(List<Category> categories) {
        super();
        this.categories = categories;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
