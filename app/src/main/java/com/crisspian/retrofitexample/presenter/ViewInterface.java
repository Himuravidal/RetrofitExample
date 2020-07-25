package com.crisspian.retrofitexample.presenter;

import androidx.lifecycle.LiveData;

import com.crisspian.retrofitexample.model.MarsObject;

import java.util.List;

public interface ViewInterface {
    void showFetchData(List<MarsObject> marsObjectList);
    void showErrorOnData(Throwable t);
}
