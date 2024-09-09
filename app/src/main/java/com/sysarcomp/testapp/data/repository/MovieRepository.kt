package com.sysarcomp.testapp.data.repository

import com.sysarcomp.testapp.data.model.MovieList
import com.sysarcomp.testapp.data.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRepository {

    private val apiService: ApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()) //
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }


    suspend fun getTrendingWeeklyMovies(): List<MovieList> {
        return withContext(Dispatchers.IO) { apiService.getTrendingWeeklyMovies() }
    }
}