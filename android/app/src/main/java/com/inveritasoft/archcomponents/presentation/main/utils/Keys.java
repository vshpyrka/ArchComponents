package com.inveritasoft.archcomponents.presentation.main.utils;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Oleksandr Kryvoruchko on 23.04.2018.
 */
@StringDef({Keys.SHARED_PREFS_KEY, Keys.IS_LOGGED_IN})
@Retention(RetentionPolicy.SOURCE)
public @interface Keys {

    /**
     * Key get shared preference instance.
     */
    String SHARED_PREFS_KEY = "User";

    String PUSH_TOKEN = "Push_token";

    String COOKIE = "Cookie";

    String USER_ID = "userId";

    /**
     * Key to store and receive user Id from Api.
     */
    String IS_LOGGED_IN = "Is_logged_in";

    /**
     * Key for Json.
     */
    String USER_NAME = "UserName";

}