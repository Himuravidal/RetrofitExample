package com.example.retrofitexample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitexample.model.Movie
import com.example.retrofitexample.model.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    private val repository = MovieRepository()

    private val _uiState = MutableStateFlow(MovieUiState())
    val uiState : StateFlow<MovieUiState> = _uiState.asStateFlow()

    init {
        loadMovies()
    }

     fun loadMovies()  {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            repository.getPopularMovies(1)
                .onSuccess { movies ->
                    _uiState.update {
                        it.copy(
                            movies = movies,
                            isLoading = false
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            error = error.message,
                            isLoading = false
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