package com.inveritasoft.archcomponents.api;

import com.inveritasoft.archcomponents.api.model.Book;
import com.inveritasoft.archcomponents.api.model.Category;

import java.util.ArrayList;
import java.util.List;

public class DataMock {

    public static List<Category> generateMockData() {
        List<Category> categories = new ArrayList<>();
        for (int index = 0; index < 5; index++) {
            Category category = new Category();
            List<Book> books = new ArrayList<>();
            for (int bookIndex = 0; bookIndex < 7; bookIndex++) {
                books.add(new Book("book" + bookIndex));
            }
            category.setBooks(books);
            categories.add(category);
        }
        return categories;
    }
}
