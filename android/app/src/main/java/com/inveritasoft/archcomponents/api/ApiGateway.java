package com.inveritasoft.archcomponents.api;

import com.inveritasoft.archcomponents.presentation.main.adapter.BookModel;

import java.util.List;

import okhttp3.Callback;

/**
 * Created by Oleksandr Kryvoruchko on 23.04.2018.
 */
public interface ApiGateway {

    void doLogin(String username, Callback callback);

    void getBooks(final int userId, Callback callback);

    void updateBooks(final int userId, List<BookModel> books, Callback callback);
}
