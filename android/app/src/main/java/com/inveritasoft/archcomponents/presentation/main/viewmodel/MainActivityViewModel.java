package com.inveritasoft.archcomponents.presentation.main.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.inveritasoft.archcomponents.App;
import com.inveritasoft.archcomponents.repository.ArchRepository;

/**
 * Created by Oleksandr Kryvoruchko on 23.04.2018.
 */
public class MainActivityViewModel extends ViewModel {

    private final ArchRepository archRepository;

    public MainActivityViewModel() {
        archRepository = App.getInstance().getRepository();
    }

    public boolean isLoggedIn() {
        return archRepository.isLoggedIn();
    }
}
