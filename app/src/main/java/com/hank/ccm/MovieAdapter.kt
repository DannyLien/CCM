package com.hank.ccm

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import coil.transform.RoundedCornersTransformation
import com.hank.ccm.databinding.RowMoveViewBinding

class MovieAdapter(val movieData: List<Movie>) : RecyclerView.Adapter<MovieViewHolder>() {
    private val TAG: String? = MovieAdapter::class.java.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val v = RowMoveViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MovieViewHolder(v)
    }

    override fun getItemCount(): Int {
        return movieData.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val data = movieData.get(position)
        holder.movieTitle.setText(data.title)
        holder.moviepop.setText(data.popularity.toString())
        holder.movieAvatar.load("https://image.tmdb.org/t/p/w500${data.poster_path}") {
            placeholder(R.drawable.rainbow).crossfade(2000)
            transformations(
                RoundedCornersTransformation(80f, 80f, 80f, 80f)
            )
        }
        holder.itemView.setOnClickListener {
            Log.d(
                TAG, "onBindViewHolder: ccm-words-movie-" +
                        " ${position} , ${data.title} , ${data.popularity} "
            )
        }
    }
}

class MovieViewHolder(var view: RowMoveViewBinding) : ViewHolder(view.root) {
    val movieTitle = view.rowMovieTitle
    val moviepop = view.rowMoviePop
    val movieAvatar = view.rowMovieAvatar
}

















