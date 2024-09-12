package com.sysarcomp.testapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sysarcomp.testapp.R
import com.sysarcomp.testapp.ui.adapter.MovieAdapter
import com.sysarcomp.testapp.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        val movieRv = findViewById<RecyclerView>(R.id.rvMovie)
        movieRv.layoutManager = LinearLayoutManager(this)


        // Inicializa el adaptador solo una vez
        movieAdapter = MovieAdapter(emptyList()) { movie ->

            val intent = Intent(this, MovieDetail::class.java)

            intent.putExtra("id", movie.id.toString())

            Log.i("patrox id" , movie.id.toString())

            startActivity(intent)
        }

        movieRv.adapter = movieAdapter


        // Observa los cambios en la lista de ítems y actualiza el adaptador
        movieViewModel.movies.observe(this, { movies ->
            movieAdapter.updateItems(movies)
        })

        movieViewModel.fetchTrendingMovies()
    }
}