package com.inveritasoft.archcomponents.presentation.main.utils;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Oleksandr Kryvoruchko on 23.04.2018.
 */
@StringDef({Keys.SHARED_PREFS_KEY, Keys.PUSH_TOKEN, Keys.COOKIE})
@Retention(RetentionPolicy.SOURCE)
public @interface Keys {

    /**
     * Key get shared preference instance.
     */
    String SHARED_PREFS_KEY = "User";

    String PUSH_TOKEN = "Push_token";

    String COOKIE = "Cookie";
}
