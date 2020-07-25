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
    private ApiService apiService;
    private ViewInterface viewInterface;
    private List<MarsObject> marsObjectList = new ArrayList<>();

    public Presenter(Application application, ViewInterface viewInterface) {
        repository = new Repository(application);
        this.viewInterface = viewInterface;
        apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
    }


    @Override
    public List<MarsObject> getFetchData() {
        Call<List<MarsObject>> call = apiService.getMarsData();
        call.enqueue(new Callback<List<MarsObject>>() {
            @Override
            public void onResponse(Call<List<MarsObject>> call, Response<List<MarsObject>> response) {
                viewInterface.showFetchData(response.body());
                Log.d("RESPONSE", response.body().get(0).getImgSrc());
            }

            @Override
            public void onFailure(Call<List<MarsObject>> call, Throwable t) {
               viewInterface.showErrorOnData(t);
            }
        });
        return marsObjectList;
    }

}
