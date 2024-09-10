package com.sysarcomp.testapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sysarcomp.testapp.model.Movie
import com.sysarcomp.testapp.network.RetrofitClient
import com.sysarcomp.testapp.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    private val repository = MovieRepository(RetrofitClient.instance)

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    fun fetchTrendingMovies() {
        viewModelScope.launch {
            try {
                val response = repository.getTrendingMovies("efbc2b95033e7dde757b6c455744baa2")
                response?.let {
                    _movies.value = it.results
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}