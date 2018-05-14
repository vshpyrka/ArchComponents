package com.inveritasoft.archcomponents.presentation.auth.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.inveritasoft.archcomponents.R;
import com.inveritasoft.archcomponents.databinding.AuthFragmentBinding;
import com.inveritasoft.archcomponents.presentation.auth.viewmodel.AuthFragmentViewModel;
import com.inveritasoft.archcomponents.presentation.main.ui.MainActivity;
import com.inveritasoft.archcomponents.presentation.main.utils.Status;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.auth_fragment, container,
                false);
        viewModel = ViewModelProviders.of(this).get(AuthFragmentViewModel.class);
        binding.setViewModel(viewModel);
        subscribeUI();
        return binding.getRoot();
    }

    private void subscribeUI() {
        viewModel.getLoginLiveData().observe(this, loginState -> {
            if (loginState != null) {
                if (loginState.status == Status.LOADING) {
                    showProgress(true);
                } else if (loginState.status == Status.SUCCESS) {
                    showProgress(false);
                    showHomeActivity();
                } else {
                    showProgress(false);
                    Toast.makeText(getContext(), "Failed to log in", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showProgress(final boolean show) {
        binding.progressBar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

    private void showHomeActivity() {
        startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finish();
    }

}
