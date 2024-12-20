package com.example.retrofitexample.domain.usecase

import com.example.retrofitexample.data.repository.MovieRepositoryImpl
import com.example.retrofitexample.domain.model.Movie
import kotlinx.coroutines.flow.Flow

class GetPopularMoviesUseCase {
    private val repository = MovieRepositoryImpl()

    suspend operator fun invoke(page: Int): Flow<List<Movie>> = repository.getPopularMovies(page)

}