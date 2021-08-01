package com.example.moviereviweapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieReview(
    @SerializedName("status")
    @Expose
    val status: String? = null,
    @SerializedName("copyright")
    @Expose
    val copyright: String? = null,
    @SerializedName("num_results")
    @Expose
    val numResults: Int? = 0,
    @SerializedName("hasMore")
    @Expose
    val hasMore: Boolean? = null,
    @SerializedName("results")
    @Expose
    val results: List<Result>
)
