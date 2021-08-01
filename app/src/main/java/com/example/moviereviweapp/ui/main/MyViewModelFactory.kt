package com.example.moviereviweapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviereviweapp.repository.MovieReviewRepository

class MyViewModelFactory constructor(
    private val movieReviewRepository: MovieReviewRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.movieReviewRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
