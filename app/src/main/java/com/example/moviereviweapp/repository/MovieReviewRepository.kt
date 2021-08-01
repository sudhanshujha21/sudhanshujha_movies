package com.example.moviereviweapp.repository

import com.example.moviereviweapp.network.RetrofitService

class MovieReviewRepository constructor(private val retrofitService: RetrofitService) {
     suspend fun getMovieReviewData() = retrofitService.getMovieReview()
}
