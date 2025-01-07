package com.example.movieapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.model.Movie

@Composable
fun MovieRow(
    movie: Movie,
    onItemClick: (String) -> Unit? = {}
) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick(movie.id)
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shape = RoundedCornerShape(16.dp),
                shadowElevation = 4.dp
            ) {
                //Manifest permission for the image fetch: <uses-permission android:name="android.permission.INTERNET" />
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.poster)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Movie Image",
                    contentScale = ContentScale.Crop
                )
            }
            MovieDetails(movie)
        }

    }
}

@Composable
fun MovieDetails(movie: Movie) {
    var expanded by remember { mutableStateOf(false) }
    Column {
        Text(
            text = movie.title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Director: ${movie.director}",
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = "Released: ${movie.released}",
            style = MaterialTheme.typography.bodySmall
        )
        AnimatedDetails(movie, expanded)
        Icon(
            imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
            contentDescription = "Down Arrow",
            modifier = Modifier
                .size(25.dp)
                .clickable {
                    expanded = !expanded
                }
        )
    }
}

@Composable
fun AnimatedDetails(movie: Movie, expanded: Boolean) {
    AnimatedVisibility(visible = expanded) {
        Column {
            Text(buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.DarkGray,
                        fontSize = MaterialTheme.typography.bodySmall.fontSize
                    )
                ) { append("Plot: ") }
                withStyle(
                    style = SpanStyle(
                        color = Color.DarkGray,
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        fontWeight = FontWeight.Light
                    )
                ) { append(movie.plot) }
            }, modifier = Modifier.padding(6.dp))
            HorizontalDivider(Modifier.padding(5.dp))
            Text(
                text = "Director: ${movie.director}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Actors: ${movie.actors}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Rating: ${movie.imdbRating}",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold
            )
        }
    }
}