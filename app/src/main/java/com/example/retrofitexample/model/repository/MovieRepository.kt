package com.example.retrofitexample.model.repository

import com.example.retrofitexample.BuildConfig
import com.example.retrofitexample.model.Movie
import com.example.retrofitexample.model.RetrofitClient

class MovieRepository {
    private val api = RetrofitClient.movieApi

    suspend fun getPopularMovies(page: Int) : Result<List<Movie>> {
        return try {
            val response = api.searchMovies(
                page = page,
                query = "The godfather"
            )
            Result.success(response.movies)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}