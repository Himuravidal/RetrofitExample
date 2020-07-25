package com.crisspian.retrofitexample.repository;

import com.crisspian.retrofitexample.model.MarsObject;

import java.util.List;

public interface Remote {
    List<MarsObject> fetchMarsData();
}
