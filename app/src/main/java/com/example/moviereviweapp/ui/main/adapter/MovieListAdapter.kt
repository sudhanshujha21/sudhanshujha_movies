package com.example.moviereviweapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviereviweapp.databinding.ItemMovieReviewBinding
import com.example.moviereviweapp.interfaces.OnMovieItemClickListener
import com.example.moviereviweapp.model.Result

class MovieListAdapter(
    private val onMovieItemClickListener: OnMovieItemClickListener
) :
    RecyclerView.Adapter<MyViewHolder>() {
    private var movies = mutableListOf<Result>()

    fun setMovieList(movies: List<Result>) {
        this.movies = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // inflate the view here.
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieReviewBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = movies[position]
        holder.binding.movieTitle.text = movie.displayTitle
        Glide.with(holder.itemView.context).load(movie.multimedia?.src)
            .into(holder.binding.imageView)
        holder.itemView.setOnClickListener {
            onMovieItemClickListener.onMovieItemClicked(position)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}

class MyViewHolder(val binding: ItemMovieReviewBinding) : RecyclerView.ViewHolder(binding.root)
