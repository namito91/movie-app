package com.sysarcomp.testapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.sysarcomp.testapp.model.Movie
import com.sysarcomp.testapp.model.moviedetails.MovieDetailResponse
import com.sysarcomp.testapp.network.RetrofitClient
import com.sysarcomp.testapp.repository.MovieRepository
import kotlinx.coroutines.launch

//Solo el ViewModel controla cómo y cuándo se actualizan los datos,
// lo que evita errores en la manipulación de los datos.

// La variable movies es la versión pública de _movies, pero es de tipo LiveData,
// lo que significa que solo se puede observar, no modificar.
// Esta es la versión que los componentes de la interfaz de usuario utilizarán para recibir
// actualizaciones de datos cuando haya cambios en las películas.

class MovieViewModel : ViewModel() {

    private val repository = MovieRepository(RetrofitClient.instance)


    //La variable _movies es un contenedor observable que puede almacenar y modificar una lista de películas.
    // Al ser MutableLiveData, puede cambiar su valor internamente dentro de la clase,
    // lo que luego notificará a cualquier componente que esté observando esos cambios
    private val _movies = MutableLiveData<List<Movie>>()

    // LiveData es una forma de manejar datos que permite a la interfaz de usuario (UI) "observar"
    // y actualizarse automáticamente cuando estos datos cambian.
    val movies: LiveData<List<Movie>> get() = _movies


    private val _movieDetailResponse = MutableLiveData<MovieDetailResponse?>()
    val movieDetailResponse: MutableLiveData<MovieDetailResponse?> = _movieDetailResponse

    fun fetchMovieDetails(id: String) {
        viewModelScope.launch {
            try {
                val response = repository.getMovieDetails(id, "efbc2b95033e7dde757b6c455744baa2")
                _movieDetailResponse.postValue(response)
            } catch (e: Exception) {
                e.printStackTrace()
                _movieDetailResponse.postValue(null)
            }
        }
    }


    fun fetchTrendingMovies() {
        viewModelScope.launch {
            try {

                val response = repository.getTrendingMovies("efbc2b95033e7dde757b6c455744baa2")

                // si la response no es nula , se asiganan los valores a _movies
                response?.let {
                    _movies.value = response.results
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }




}