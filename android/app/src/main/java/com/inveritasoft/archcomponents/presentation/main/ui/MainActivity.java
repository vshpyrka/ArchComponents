package com.inveritasoft.archcomponents.presentation.main.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.inveritasoft.archcomponents.App;
import com.inveritasoft.archcomponents.R;
import com.inveritasoft.archcomponents.presentation.auth.ui.AuthActivity;
import com.inveritasoft.archcomponents.repository.ArchRepository;

/**
 * Application launcher activity.
 */
public class MainActivity extends AppCompatActivity {

    ArchRepository archRepository;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataBindingUtil.setContentView(this, R.layout.activity_main);
        archRepository = App.getInstance().getRepository();
        checkIfIsLoggedIn();
    }

    private void checkIfIsLoggedIn() {
        if (archRepository.getIsLoggedIn().getValue() != null && archRepository.getIsLoggedIn().getValue()) {
            showDataFragment();
        } else {
            showLoginScreen();
        }
    }

    private void showLoginScreen() {
        final Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
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
}
