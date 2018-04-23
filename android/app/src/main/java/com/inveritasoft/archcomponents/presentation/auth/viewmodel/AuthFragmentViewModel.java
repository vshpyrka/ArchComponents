package com.inveritasoft.archcomponents.presentation.auth.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.inveritasoft.archcomponents.api.ApiGateway;
import com.inveritasoft.archcomponents.api.ApiGatewayImpl;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Oleksandr Kryvoruchko on 23.04.2018.
 */
public class AuthFragmentViewModel extends ViewModel {

    ApiGateway apiGateway = ApiGatewayImpl.getInstance();
    public final ObservableField<String> username = new ObservableField<>("");


    /**
     * Called when Button log in clicked.
     */
    public void onLoginButtonClick() {
        apiGateway.doLogin(username.get(), new Callback() {
            @Override
            public void onFailure(final Call call, IOException e) {

            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {

            }
        });
    }
}
