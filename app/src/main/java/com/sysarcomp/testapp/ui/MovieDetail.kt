package com.sysarcomp.testapp.ui

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.sysarcomp.testapp.R
import com.sysarcomp.testapp.model.moviedetails.MovieDetailResponse
import com.sysarcomp.testapp.viewmodel.MovieViewModel
import java.text.NumberFormat
import java.util.Locale

class MovieDetail : AppCompatActivity() {

    private lateinit var movieViewModel: MovieViewModel

    //(image, title, description, genres, budget, popularity and release day
    private lateinit var image: ImageView
    private lateinit var title: TextView
    private lateinit var description: TextView
    private lateinit var genres: TextView
    private lateinit var budget: TextView
    private lateinit var popularity: TextView
    private lateinit var releaseDay: TextView


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movie_detail)

        // se enlazan los elementos de la vista
        initUI()

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
                getMovieDetailResponse(it)
            }
        })
    }


    private fun initUI() {

        image = findViewById(R.id.ivMovieDetail)
        title = findViewById(R.id.movieDetailTitle)
        description = findViewById(R.id.movieDetailDescription)
        genres = findViewById(R.id.movieDetailGenre)
        budget = findViewById(R.id.movieDetailBudget)
        popularity = findViewById(R.id.movieDetailPopularity)
        releaseDay = findViewById(R.id.movieDetailReleaseDay)

    }


    private fun getMovieDetailResponse(movieDetailResponse: MovieDetailResponse) {

        //(image, title, description, genres, budget, popularity and release day
        Picasso.get().load(movieDetailResponse.poster_path).into(image)

        title.text = movieDetailResponse.original_title
        description.text = movieDetailResponse.overview

        // Convierte la lista de géneros a una cadena
        val genresString = movieDetailResponse.genres.joinToString(", ") { genre -> genre.name }
        val formattedGenres = String.format("Genres: %s", genresString)
        genres.text = formattedGenres

        // formatea el presupuesto con el símbolo $ y formato adecuado
        val budgetValue = movieDetailResponse.budget ?: 0 // valiodar que el valor no sea nulo
        val formattedBudget = String.format(
            "Budget: %s",
            NumberFormat.getCurrencyInstance(Locale.US).format(budgetValue)
        )

        budget.text = formattedBudget

        val formattedPopularity =
            String.format("popularity: %s", movieDetailResponse.popularity.toString())
        popularity.text = formattedPopularity

        val formattedReleaseDay =
            String.format("Release date: %s", movieDetailResponse.release_date)
        releaseDay.text = formattedReleaseDay


        // Log.i("title" , movieDetailResponse.original_title)
    }


}