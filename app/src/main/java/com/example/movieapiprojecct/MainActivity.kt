package com.example.movieapiprojecct

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapiprojecct.databinding.ActivityMainBinding
import com.example.movieapiprojecct.models.Movie
import com.example.movieapiprojecct.models.MovieResponse
import com.example.movieapiprojecct.services.MovieApiInterface
import com.example.movieapiprojecct.services.MovieApiService

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
//import com.example.movieapiprojecct.services.MovieResponse
private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.rvMoviesList.apply {

            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            getMovieData { movies: List<Movie> ->
                adapter = MovieAdapter(movies)
            }
        }
    }


    private fun getMovieData(callback: (List<Movie>) -> Unit){
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getMovieList().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }

        })
    }
}