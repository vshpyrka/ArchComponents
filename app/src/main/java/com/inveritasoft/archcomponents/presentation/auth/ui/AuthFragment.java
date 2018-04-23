package com.inveritasoft.archcomponents.presentation.auth.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inveritasoft.archcomponents.R;
import com.inveritasoft.archcomponents.databinding.AuthFragmentBinding;
import com.inveritasoft.archcomponents.presentation.auth.viewmodel.AuthFragmentViewModel;

/**
 * Created by Oleksandr Kryvoruchko on 23.04.2018.
 */
public class AuthFragment extends Fragment {

    /**
     * Fragment Tag name.
     */
    public static final String TAG = "AuthenticationFragment";

    private AuthFragmentViewModel viewModel;

    private AuthFragmentBinding binding;

    /**
     * Stub method which provides new fragment instance.
     *
     * @return New fragment instance
     */
    public static AuthFragment newInstance() {
        return new AuthFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.auth_fragment, container,
                false);
        viewModel = ViewModelProviders.of(this).get(AuthFragmentViewModel.class);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    private void showProgress(final boolean show) {
        binding.progressBar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

}
