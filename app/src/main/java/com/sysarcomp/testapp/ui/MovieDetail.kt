package com.sysarcomp.testapp.ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.sysarcomp.testapp.R
import com.sysarcomp.testapp.model.moviedetails.MovieDetailResponse
import com.sysarcomp.testapp.viewmodel.MovieViewModel

class MovieDetail : AppCompatActivity() {

    private lateinit var movieViewModel: MovieViewModel

    private lateinit var title: TextView


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movie_detail)

        title = findViewById(R.id.movieDetailTitle)


        // Configura el botón para volver a la pantalla anterior
        val backButton: ImageButton = findViewById(R.id.btnBack)
        backButton.setOnClickListener {
            onBackPressed() // Volver a la pantalla anterior
        }


        // tomo el id del movie seleccionado
        // aseguro que siempre retorne un string , vacio o no
        val id = intent.getStringExtra("id").orEmpty()

        // Log.i("patrox2" , id)

        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        // Llama a fetchMovieDetails y observa la respuesta
        movieViewModel.fetchMovieDetails(id)

        movieViewModel.movieDetailResponse.observe(this, { response ->
            // Actualiza la UI cuando se reciba la respuesta
            response?.let {
                initUI(it)
            }
        })
    }


    private fun initUI(movieDetailResponse: MovieDetailResponse) {

        title.text = movieDetailResponse.original_title

        // Log.i("title" , movieDetailResponse.original_title)
    }


}