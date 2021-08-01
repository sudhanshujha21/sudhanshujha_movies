package com.example.moviereviweapp.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviereviweapp.model.MovieReview
import com.example.moviereviweapp.repository.MovieReviewRepository
import kotlinx.coroutines.launch

class MainViewModel(private val movieRepository: MovieReviewRepository) :
    ViewModel() {
    var movieReview: MutableLiveData<MovieReview> = MutableLiveData<MovieReview>()
     val errorMessage = MutableLiveData<String>()


    fun getMovieData() = viewModelScope.launch {
        movieRepository.getMovieReviewData().let { response ->
            if (response.isSuccessful) {
                movieReview.value = response.body()
                Log.d("onResponse", " - > onResponse    ${response.body()}")
            } else {
                Log.d("getMovieReview", "- > Error: ${response.code()}")
                errorMessage.value = response.message()
            }
        }
    }
}
