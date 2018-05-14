package com.inveritasoft.archcomponents.api;

import com.inveritasoft.archcomponents.presentation.main.adapter.BookModel;

import java.util.List;

import okhttp3.Callback;

/**
 * Created by Oleksandr Kryvoruchko on 23.04.2018.
 */
public interface ApiGateway {

    void login(String username, Callback callback);

    void getBooks(Callback callback);

    void updateBooks(List<BookModel> books, Callback callback);
}
