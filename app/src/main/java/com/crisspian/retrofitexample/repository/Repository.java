package com.crisspian.retrofitexample.repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.crisspian.retrofitexample.Remote.ApiClient;
import com.crisspian.retrofitexample.Remote.ApiService;
import com.crisspian.retrofitexample.model.MarsObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Repository implements Remote {

    private ApiService apiService;
    private LiveData<List<MarsObject>> listLiveData;
    private List<MarsObject> marsObjectList;
    private MutableLiveData<ArrayList<MarsObject>> listMutableLiveData = new MutableLiveData<>();

    public Repository(Application application) {
         apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
        //TodoRoomDatabase db = TodoRoomDatabase.getDatabase(application);
        //todoDao = db.todoDao();
        //listLiveData = todoDao.getallTodoFromDB();
    }

    //THis is not in use, but the idea with this is receive the data and save it with room.
    //
    @Override
    public List<MarsObject> fetchMarsData() {
        Call<List<MarsObject>> call = apiService.getMarsData();
        call.enqueue(new Callback<List<MarsObject>>() {
            @Override
            public void onResponse(Call<List<MarsObject>> call, Response<List<MarsObject>> response) {
                marsObjectList = response.body();
                Log.d("RESPONSE", response.body().get(0).getImgSrc());
            }
            @Override
            public void onFailure(Call<List<MarsObject>> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
        return marsObjectList;
    }
}
