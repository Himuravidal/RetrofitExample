package com.example.retrofitexample.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitexample.domain.model.Movie
import com.example.retrofitexample.domain.usecase.GetPopularMoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    private val getPopularMoviesUseCase = GetPopularMoviesUseCase()

    private val _uiState = MutableStateFlow(MovieUiState())
    val uiState : StateFlow<MovieUiState> = _uiState.asStateFlow()

    private var currentPage = 1

    init {
        loadMovies()
    }

     fun loadMovies()  {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                getPopularMoviesUseCase(page = currentPage)
                    .collect { movies ->
                        _uiState.update {
                            it.copy(
                                movies = movies,
                                isLoading = false
                            )
                        }

                    }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message
                    )
                }
            }

        }
    }

}

data class MovieUiState(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)