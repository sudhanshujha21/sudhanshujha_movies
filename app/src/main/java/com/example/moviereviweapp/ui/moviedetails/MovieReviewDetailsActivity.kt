package com.example.moviereviweapp.ui.moviedetails
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.moviereviweapp.databinding.ActivityMovieReviewDetailsBinding

class MovieReviewDetailsActivity : AppCompatActivity() {
    private lateinit var bindingDetails: ActivityMovieReviewDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_movie_review_details)
        bindingDetails = ActivityMovieReviewDetailsBinding.inflate(layoutInflater)
        setContentView(bindingDetails.root)

        // get intent object, and get data from intent.
        val intent = intent
        val movieSummary = intent.getStringExtra("summaryShort")
        val movieImage = intent.getStringExtra("src")
        bindingDetails.movieSummary.text = movieSummary
        Glide.with(applicationContext).load(movieImage)
            .into(bindingDetails.imageView)
    }
}
