package com.crisspian.retrofitexample.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.crisspian.retrofitexample.Remote.ApiClient;
import com.crisspian.retrofitexample.Remote.ApiService;
import com.crisspian.retrofitexample.model.MarsObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository implements Remote {

    private ApiService apiService;
    private List<MarsObject> marsObjectList;
    private MutableLiveData<List<MarsObject>> listMutableLiveData = new MutableLiveData<>();

    public Repository() {
         apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
    }


    @Override
    public LiveData<List<MarsObject>> fetchMarsData() {
        Call<List<MarsObject>> call = apiService.getMarsData();
        call.enqueue(new Callback<List<MarsObject>>() {
            @Override
            public void onResponse(Call<List<MarsObject>> call, Response<List<MarsObject>> response) {
                marsObjectList = response.body();
                listMutableLiveData.setValue(marsObjectList);
             //   Log.d("RESPONSE", response.body().get(0).getImgSrc());
            }
            @Override
            public void onFailure(Call<List<MarsObject>> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
        return listMutableLiveData;
    }
}
