package com.crisspian.retrofitexample.Remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com";
    private static Retrofit retrofit = null;

    //This method return a retrofit client instance
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    //.addCallAdapterFactory()
                    .build();
        }
        return retrofit;
    }

}
