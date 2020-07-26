package com.crisspian.retrofitexample.presenter;

import androidx.lifecycle.LiveData;

import com.crisspian.retrofitexample.model.MarsObject;

import java.util.List;

public interface PresenterInterface {
    LiveData<List<MarsObject>> fetchLivedata();
}
