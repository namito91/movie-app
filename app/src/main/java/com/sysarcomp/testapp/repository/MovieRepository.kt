package com.sysarcomp.testapp.repository

import com.sysarcomp.testapp.model.MovieResponse
import com.sysarcomp.testapp.model.moviedetails.MovieDetailResponse
import com.sysarcomp.testapp.network.ApiService

class MovieRepository(private val apiService: ApiService) {


    suspend fun getTrendingMovies(apiKey: String): MovieResponse? {
        return try {
            apiService.getTrendingMovies(apiKey)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }


    suspend fun getMovieDetails(id :String,apiKey: String): MovieDetailResponse? {

        return try {
            apiService.getMovieDetails(id,apiKey)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
