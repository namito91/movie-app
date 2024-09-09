package com.sysarcomp.testapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sysarcomp.testapp.data.model.MovieList
import com.sysarcomp.testapp.data.repository.MovieRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val movieRepository = MovieRepository()
    private val _movies = MutableLiveData<List<MovieList>>()
    val user: LiveData<List<MovieList>> get() = _movies

    fun fetchMovies() {
        viewModelScope.launch {

            try {
                val fetchedMovies: List<MovieList> = movieRepository.getTrendingWeeklyMovies()
                _movies.value = fetchedMovies

            } catch (e: Exception) {

                // Manejar el error
                _movies.value = emptyList()
            }
        }
    }
}