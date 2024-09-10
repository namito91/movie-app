package com.sysarcomp.testapp.network

import com.sysarcomp.testapp.model.Movie
import com.sysarcomp.testapp.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("trending/movie/week")
    suspend fun getTrendingMovies(

        @Query("api_key")
        apiKey: String

    ): MovieResponse
}
