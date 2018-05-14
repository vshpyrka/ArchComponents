package com.inveritasoft.archcomponents.repository;

import android.arch.lifecycle.LiveData;

import com.inveritasoft.archcomponents.db.entities.BookEntity;
import com.inveritasoft.archcomponents.presentation.main.adapter.BookModel;

import java.util.List;

/**
 * Created by Oleksandr Kryvoruchko on 24.04.2018.
 */
public interface ArchRepository {

    boolean isLoggedIn();

    void login(String user, LoginCallback callback);

    void getBooksFromApi();

    LiveData<List<BookEntity>> getBooks();

    void updateBooks(List<BookModel> books);
}
