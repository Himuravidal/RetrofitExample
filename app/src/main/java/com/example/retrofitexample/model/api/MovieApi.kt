package com.example.retrofitexample.model.api

import com.example.retrofitexample.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page:  Int = 1,
        @Query("api_key") apiKey: String
    ): MovieResponse

}