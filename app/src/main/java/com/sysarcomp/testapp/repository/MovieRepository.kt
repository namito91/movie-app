package com.sysarcomp.testapp.repository

import com.sysarcomp.testapp.model.Movie
import com.sysarcomp.testapp.model.MovieResponse
import com.sysarcomp.testapp.network.ApiService
import com.sysarcomp.testapp.network.RetrofitClient

class MovieRepository(private val apiService: ApiService) {

    suspend fun getTrendingMovies(apiKey: String): MovieResponse? {
        return try {
            apiService.getTrendingMovies(apiKey)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
