package com.inveritasoft.archcomponents.presentation.auth.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.inveritasoft.archcomponents.R;

/**
 * Created by Oleksandr Kryvoruchko on 23.04.2018.
 */
public class AuthActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_activity);

        showFragment();
    }

    private void showFragment() {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        AuthFragment authFragment = (AuthFragment) fragmentManager
                .findFragmentByTag(AuthFragment.TAG);
        if (authFragment == null) {
            authFragment = AuthFragment.newInstance();
            final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.auth_container, authFragment, AuthFragment.TAG)
                    .commit();
        }
    }
}
