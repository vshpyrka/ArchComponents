package com.inveritasoft.archcomponents.presentation.auth.viewmodel;

import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.inveritasoft.archcomponents.App;
import com.inveritasoft.archcomponents.repository.ArchRepository;

/**
 * Created by Oleksandr Kryvoruchko on 23.04.2018.
 */
public class AuthFragmentViewModel extends ViewModel {

    public final ArchRepository repository = App.getInstance().getRepository();


    /**
     * Called when Button log in clicked.
     */
    public void onLoginButtonClick() {
        repository.doApiLogin(username.get());
    }

    public MediatorLiveData<Boolean> getIsLoggedIn() {
        return repository.getIsLoggedIn();
    }
}
