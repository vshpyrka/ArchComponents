package com.inveritasoft.archcomponents.repository;

public interface LoginCallback {

    void onSuccess();

    void onFail(Throwable throwable);
}
