package com.inveritasoft.archcomponents.presentation.main.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.inveritasoft.archcomponents.R;
import com.inveritasoft.archcomponents.presentation.auth.ui.AuthActivity;
import com.inveritasoft.archcomponents.presentation.main.viewmodel.MainActivityViewModel;

/**
 * Application launcher activity.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_main);

        final MainActivityViewModel viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        if (viewModel.isLoggedIn()) {
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
            fragment = new DataFragment();
            fragmentTransaction.add(R.id.main_container, fragment, DataFragment.TAG).commit();
        }
    }
}
