package com.sysarcomp.testapp.data.remote

import com.sysarcomp.testapp.data.model.MovieList
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("3/trending/movie/week?api_key=efbc2b95033e7dde757b6c455744baa2")
    suspend fun getTrendingWeeklyMovies(): List<MovieList>
}