package com.inveritasoft.archcomponents.api;

import okhttp3.Callback;

/**
 * Created by Oleksandr Kryvoruchko on 23.04.2018.
 */
public interface ApiGateway {

    void doLogin(String username, Callback callback);

    void getBooks(Callback callback);
}
