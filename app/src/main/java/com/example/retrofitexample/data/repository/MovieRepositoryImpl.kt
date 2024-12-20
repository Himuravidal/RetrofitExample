package com.example.retrofitexample.data.repository

import com.example.retrofitexample.data.remote.client.RetrofitClient
import com.example.retrofitexample.data.remote.mapper.MovieMapper
import com.example.retrofitexample.domain.model.Movie
import com.example.retrofitexample.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class MovieRepositoryImpl : MovieRepository {
    private val api = RetrofitClient.movieApi
    private val mapper = MovieMapper()
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun getPopularMovies(page: Int): Flow<List<Movie>> = flow {
        val response = api.getPopularMovies(page)
        emit(response.movies.map { mapper.toDomain(it) })
    }.catch { error ->
        throw error
    }.flowOn(dispatcher)


    override suspend fun searchMovies(query: String, page: Int): Flow<List<Movie>> {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieDetails(movieId: Int): Flow<Movie> {
        TODO("Not yet implemented")
    }
}