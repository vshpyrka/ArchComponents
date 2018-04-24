package com.inveritasoft.archcomponents.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import com.inveritasoft.archcomponents.db.entities.BookEntity;
import com.inveritasoft.archcomponents.presentation.main.adapter.BookModel;

import java.util.List;

/**
 * Created by Oleksandr Kryvoruchko on 24.04.2018.
 */
public interface ArchRepository {

    void doApiLogin(String user);

    void getBooksFromApi();

    LiveData<List<BookEntity>> getBooks();

    MediatorLiveData<Boolean> getIsLoggedIn();

    void updateBooks(List<BookModel> books);
}
