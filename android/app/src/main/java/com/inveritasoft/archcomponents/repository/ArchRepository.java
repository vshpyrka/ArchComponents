package com.inveritasoft.archcomponents.repository;

import android.arch.lifecycle.MediatorLiveData;

import com.inveritasoft.archcomponents.db.entities.CategoryWithBooks;

import java.util.List;

/**
 * Created by Oleksandr Kryvoruchko on 24.04.2018.
 */
public interface ArchRepository {

    void doApiLogin(String user);

    void getBooksFromApi();

    MediatorLiveData<List<CategoryWithBooks>> getCategoriesWithBooks();

    MediatorLiveData<Boolean> getIsLoggedIn();
}
