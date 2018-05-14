package com.inveritasoft.archcomponents.presentation.auth.viewmodel;

import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.inveritasoft.archcomponents.App;
import com.inveritasoft.archcomponents.presentation.main.utils.Resource;
import com.inveritasoft.archcomponents.repository.ArchRepository;
import com.inveritasoft.archcomponents.repository.LoginCallback;

/**
 * Created by Oleksandr Kryvoruchko on 23.04.2018.
 */
public class AuthFragmentViewModel extends ViewModel {

    public final ObservableField<String> username = new ObservableField<>("");

    private final ArchRepository repository;
    private final MediatorLiveData<Resource<Boolean>> loginStateLiveData = new MediatorLiveData<>();

    public AuthFragmentViewModel() {
        repository = App.getInstance().getRepository();
    }

    /**
     * Called when Button log in clicked.
     */
    public void onLoginButtonClick() {
        loginStateLiveData.postValue(Resource.loading(null));
        repository.login(username.get(), new LoginCallback() {

            @Override
            public void onSuccess() {
                loginStateLiveData.postValue(Resource.success(true));
            }

            @Override
            public void onFail(final Throwable throwable) {
                loginStateLiveData.postValue(Resource.error(throwable.getMessage(), false));
            }
        });
    }

    public MediatorLiveData<Resource<Boolean>> getLoginLiveData() {
        return loginStateLiveData;
    }
}
