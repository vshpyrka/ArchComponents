package com.inveritasoft.archcomponents.presentation.main.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.inveritasoft.archcomponents.R;
import com.inveritasoft.archcomponents.presentation.auth.ui.AuthActivity;
import com.inveritasoft.archcomponents.presentation.main.utils.Keys;

/**
 * Application launcher activity.
 */
public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataBindingUtil.setContentView(this, R.layout.activity_main);
        initSharedPrefs();
        showDataFragment();
    }

    private void showLoginScreen() {
        final Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
    }


    private void showDataFragment() {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(DataFragment.TAG);
        if (fragment == null) {
            final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragment = DataFragment.newInstance();
            fragmentTransaction.add(R.id.main_container, fragment, DataFragment.TAG).commit();
        }
    }

    private void initSharedPrefs() {
        sharedPreferences = getSharedPreferences(Keys.SHARED_PREFS_KEY, MODE_PRIVATE);
    }
}
