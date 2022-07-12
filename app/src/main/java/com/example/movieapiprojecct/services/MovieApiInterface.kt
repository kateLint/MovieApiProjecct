package com.example.movieapiprojecct.services

import com.example.movieapiprojecct.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieApiInterface {

    @GET("3/movie/popular?api_key=c66565aa9451904e9c28f8cd64e377f6")
    fun getMovieList(): Call<MovieResponse>

}