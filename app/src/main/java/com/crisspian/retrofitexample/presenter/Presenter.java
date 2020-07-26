package com.crisspian.retrofitexample.presenter;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.crisspian.retrofitexample.Remote.ApiClient;
import com.crisspian.retrofitexample.Remote.ApiService;
import com.crisspian.retrofitexample.model.MarsObject;
import com.crisspian.retrofitexample.repository.Repository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Presenter implements PresenterInterface {

    private Repository repository;
    private ViewInterface viewInterface;

    public Presenter(ViewInterface viewInterface) {
        repository = new Repository();
        this.viewInterface = viewInterface;
    }


    @Override
    public LiveData<List<MarsObject>> fetchLivedata() {
        return repository.fetchMarsData();
    }

}
