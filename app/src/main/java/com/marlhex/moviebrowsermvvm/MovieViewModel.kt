package com.marlhex.moviebrowsermvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieViewModel(private val api: MovieApi) : ViewModel() {
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies.asStateFlow()

    private var currentPage = 1
    private var isLoading = false

    init {
        loadMovies()
    }

    private fun loadMovies(page: Int = 1) {
        if (isLoading) return

        isLoading = true
        viewModelScope.launch {
            api.getMovies(page).onSuccess { response ->
                val currentList = _movies.value.toMutableList()
                currentList.addAll(response.results)
                _movies.value = currentList
            }
            isLoading = false
        }
    }

    fun onMovieAppear(movie: Movie) {
        if (_movies.value.lastOrNull() == movie && !isLoading) {
            currentPage++
            loadMovies(currentPage)
        }
    }
}