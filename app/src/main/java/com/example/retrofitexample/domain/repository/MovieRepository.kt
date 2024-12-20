package com.example.retrofitexample.domain.repository

import com.example.retrofitexample.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getPopularMovies(page: Int): Flow<List<Movie>>
    suspend fun searchMovies(query: String, page: Int): Flow<List<Movie>>
    suspend fun getMovieDetails(movieId: Int): Flow<Movie>
}