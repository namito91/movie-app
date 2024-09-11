package com.sysarcomp.testapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.sysarcomp.testapp.R
import com.sysarcomp.testapp.model.Movie

class MovieAdapter(private var movies: List<Movie>, private val onClick: (Movie) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.movieTitle)
        val overview: TextView = view.findViewById(R.id.movieOverview)
        val imageView: ImageView = view.findViewById(R.id.moviePoster)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)

        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val movie = movies[position]

        holder.title.text = movie.title
        holder.overview.text = movie.overview

        // carga la imagen del api response , en nuestra vista
        Picasso.get()
            .load(movie.poster_path) // URL de la imagen
            .into(holder.imageView) // ImageView donde se mostrará la imagen


        // TODO
        holder.itemView.setOnClickListener {
            onClick(movie)
        }
    }

    override fun getItemCount() = movies.size


    // Método para actualizar los ítems del adaptador
    fun updateItems(newMovies: List<Movie>) {
        this.movies = newMovies
        notifyDataSetChanged()  // Notifica al RecyclerView que los datos han cambiado
    }


}