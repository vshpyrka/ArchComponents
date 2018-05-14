package com.inveritasoft.archcomponents.presentation.main.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.inveritasoft.archcomponents.presentation.main.utils.Status.ERROR;
import static com.inveritasoft.archcomponents.presentation.main.utils.Status.LOADING;
import static com.inveritasoft.archcomponents.presentation.main.utils.Status.SUCCESS;

/**
 * A generic class that describes a data with a status.
 *
 * @param <T> Data type
 */
public class Resource<T> {

    @NonNull
    public final Status status;

    @Nullable
    public final T data;

    @Nullable
    public final String message;

    private Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(ERROR, data, msg);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(LOADING, data, null);
    }
}
