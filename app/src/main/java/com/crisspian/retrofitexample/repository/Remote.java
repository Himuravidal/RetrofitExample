package com.crisspian.retrofitexample.repository;

import androidx.lifecycle.LiveData;

import com.crisspian.retrofitexample.model.MarsObject;

import java.util.List;

public interface Remote {
   LiveData<List<MarsObject>> fetchMarsData();
}
