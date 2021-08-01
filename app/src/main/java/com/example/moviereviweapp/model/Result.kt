package com.example.moviereviweapp.model
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("display_title")
    @Expose
    val displayTitle: String? = null,
    @SerializedName("headline")
    @Expose
    val headline: String? = null,
    @SerializedName("summary_short")
    @Expose
    val summaryShort: String? = null,
    val multimedia: Multimedia? = null
)
