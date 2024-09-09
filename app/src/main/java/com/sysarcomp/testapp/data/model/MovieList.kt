package com.sysarcomp.testapp.data.model

import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String
)
