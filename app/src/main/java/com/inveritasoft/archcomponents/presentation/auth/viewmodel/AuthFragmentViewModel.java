package com.inveritasoft.archcomponents.presentation.auth.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

/**
 * Created by Oleksandr Kryvoruchko on 23.04.2018.
 */
public class AuthFragmentViewModel extends ViewModel {

    public final ObservableField<String> username = new ObservableField<>("");


    /**
     * Called when Button log in clicked.
     */
    public void onLoginButtonClick() {

    }
}
