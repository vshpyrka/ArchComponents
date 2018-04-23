package com.inveritasoft.archcomponents.presentation.main.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.inveritasoft.archcomponents.api.DataMock;
import com.inveritasoft.archcomponents.api.model.Book;
import com.inveritasoft.archcomponents.api.model.Category;
import com.inveritasoft.archcomponents.presentation.main.adapter.BaseView;
import com.inveritasoft.archcomponents.presentation.main.adapter.BookModel;
import com.inveritasoft.archcomponents.presentation.main.adapter.CategoryModel;

import java.util.ArrayList;
import java.util.List;

public class DataFragmentViewModel extends ViewModel {

    private final String TYPE_CATEGORY = "category";
    private final String TYPE_BOOK = "book";


    public List<BaseView> getBooks() {
        List<Category> categories = DataMock.generateMockData();
        List<BaseView> booksList = new ArrayList<>();
        for (Category category : categories) {
            booksList.add(new CategoryModel(TYPE_CATEGORY, category.getName(), categories.indexOf(category)));
            for (Book book : category.getBooks()) {
                booksList.add(new BookModel(TYPE_BOOK, book.getName(), category.getBooks().indexOf(book) + categories.indexOf(category)));
            }
        }
        return booksList;
    }
}
