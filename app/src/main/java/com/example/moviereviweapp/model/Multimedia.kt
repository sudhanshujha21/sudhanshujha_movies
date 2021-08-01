package com.example.moviereviweapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Multimedia(
    @SerializedName("type")
    @Expose
    val type: String? = null,
    @SerializedName("src")
    @Expose
    val src: String? = null
)
