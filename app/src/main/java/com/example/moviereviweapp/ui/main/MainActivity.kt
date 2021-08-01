package com.example.moviereviweapp.ui.main
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviereviweapp.R
import com.example.moviereviweapp.databinding.ActivityMainBinding
import com.example.moviereviweapp.interfaces.OnMovieItemClickListener
import com.example.moviereviweapp.model.Result
import com.example.moviereviweapp.network.RetrofitService
import com.example.moviereviweapp.repository.MovieReviewRepository
import com.example.moviereviweapp.ui.main.adapter.MovieListAdapter
import com.example.moviereviweapp.ui.moviedetails.MovieReviewDetailsActivity
import com.example.moviereviweapp.utils.*

class MainActivity : AppCompatActivity(), OnMovieItemClickListener {
    private lateinit var mainViewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()
    private lateinit var binding: ActivityMainBinding
    var movies = ArrayList<Result>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        // initializing the auto- created file for layout.
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRv()
    }

    /**
     * Here initialize the view component.
     */
    private fun setUpRv() {
        // initialize the recycler view component.
        binding.recyclerView.apply {
            // adding a layout manager
            layoutManager = LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.VERTICAL,
                false
            )
            setHasFixedSize(true)
        }

        // initialize the view model
        mainViewModel =
            ViewModelProvider(
                this,
                MyViewModelFactory(MovieReviewRepository(retrofitService))
            ).get(
                MainViewModel::
                class.java
            )
        handleNetworkChanges()


    }

    /**
    Here checking the network connectivity
     */

    private fun handleNetworkChanges() {
        NetworkConnectivityUtils.getNetworkLiveData(applicationContext)
            .observe(this) { isConnected ->
                if (!isConnected) {
                    binding.progressBar.visibility = View.GONE
                    binding.networkStatusLayout.visibility = View.VISIBLE
                    binding.txtNetworkStatue.text = getString(R.string.no_network_txt)
                    binding.networkStatusLayout.apply {
                        show()
                        setBackgroundColor(getColorRes(R.color.colorRed))
                    }
                    showToast(getString(R.string.network_error_message))

                } else {
                    binding.networkStatusLayout.apply {
                        show()
                        setBackgroundColor(getColorRes(R.color.colorGreen))
                    }
                    binding.txtNetworkStatue.text = getString(R.string.network_connectivity_txt)
                    getMovieReviewPosts()

                }
            }
    }

    private fun getMovieReviewPosts() {
        binding.networkStatusLayout.visibility = View.VISIBLE
        binding.progressBar.visibility = View.VISIBLE
        mainViewModel.movieReview.observe(this, {
            binding.progressBar.visibility = View.GONE
            // crating an arraylist to store movies using the data class user
            movies = it.results as ArrayList<Result>
            // creating our adapter
            val adapter = MovieListAdapter(movies, this)
            adapter.setMovieList(it.results)
            // now adding the adapter to recyclerview
            binding.recyclerView.adapter = adapter
        })

        mainViewModel.errorMessage.observe(this,
            {
                Log.e("errorMessage", "errorMessage: $it")
                binding.progressBar.visibility = View.GONE
                Toast.makeText(
                    this,
                    "Opps! Something wrong in network server set up.",
                    Toast.LENGTH_SHORT
                ).show()

            })
        mainViewModel.getMovieData()
        binding.networkStatusLayout.visibility = View.GONE


    }


    override fun onMovieItemClicked(position: Int) {
        val intent = Intent(this, MovieReviewDetailsActivity::class.java)
        intent.putExtra("src", movies[position].multimedia?.src)
        intent.putExtra("summaryShort", movies[position].summaryShort)
        startActivity(intent)
    }
}
