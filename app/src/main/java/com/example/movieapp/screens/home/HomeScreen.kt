package com.example.movieapp.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies
import com.example.movieapp.navigation.MovieScreens
import com.example.movieapp.widgets.MovieRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "MovieApp",
                        color = MaterialTheme.colorScheme.background,
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily.SansSerif,
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(Color(0xD2222F87))
            )
        })
    {
        MainContent(navController = navController, paddingValues = it)
    }
}


@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    paddingValues: PaddingValues,
    movieList: List<Movie> = getMovies(LocalContext.current)
) {
    Column(modifier = modifier.padding(paddingValues)) {
        LazyColumn {
            items(movieList) {
                MovieRow(movie = it, onItemClick = { movie ->
                    navController.navigate(route = MovieScreens.DetailsScreen.name + "/$movie")
                })
            }
        }

    }
}

