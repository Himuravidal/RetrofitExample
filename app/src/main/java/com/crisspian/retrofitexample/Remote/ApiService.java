package com.crisspian.retrofitexample.Remote;

import androidx.lifecycle.LiveData;

import com.crisspian.retrofitexample.model.MarsObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @GET("realestate")
    Call<List<MarsObject>> getMarsData();

    //This is and example of a request with query params
    //@GET("endPoint")
    //Call<MarsObject> getMarsDataQueryExample(@Query("example")String param1, @Query("example") String param2);
    //THis are and example of POST
    //@POST
    //Call<OBJECTSEND> sendObjecToServer(@Body UploadProductionReport uploadProductionReport);

}
