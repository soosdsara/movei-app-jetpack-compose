package com.example.movieapp.model

import android.content.Context
import android.util.Log
import java.io.IOException
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.util.UUID

data class Movie(
    var id: String = "",
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("Rated") val rated: String,
    @SerializedName("Released") val released: String,
    @SerializedName("Runtime") val runtime: String,
    @SerializedName("Genre") val genre: String,
    @SerializedName("Director") val director: String,
    @SerializedName("Writer") val writer: String,
    @SerializedName("Actors") val actors: String,
    @SerializedName("Plot") val plot: String,
    @SerializedName("Language") val language: String,
    @SerializedName("Country") val country: String,
    @SerializedName("Poster") val poster: String,
    @SerializedName("Images") val images: List<String>,
    val imdbRating: String,
    val imdbLink: String
)

private var cachedMovies: List<Movie>? = null

private fun parseMoviesFromJSON(context: Context): List<Movie> {
    val json: String
    try {
        json = context.assets.open("Film.json").bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        Log.e("parseMoviesFromJSON", "Failed to load JSON file", ioException)
        return emptyList()
    }

    val gson = Gson()
    val listType = object : TypeToken<List<Movie>>() {}.type
    val data: List<Movie> = gson.fromJson(json, listType)

    return data.map { movie ->
        movie.apply { id = UUID.randomUUID().toString() }
    }
}

fun getMovies(context: Context): List<Movie> {
    if (cachedMovies == null) {
        cachedMovies = parseMoviesFromJSON(context)
    }
    return cachedMovies ?: emptyList()
}


