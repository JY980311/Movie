package com.example.movie.common

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    posterPath: String,
    movieName: String,
    voteAverage: Double = 0.0,
    onClick: () -> Unit
) {
    val imageBrushColor = Brush.verticalGradient(
        0.0f to Color.Black.copy(alpha = 0f),
        0.1f to Color.Black.copy(alpha = 0.1f),
        0.2f to Color.Black.copy(alpha = 0.2f),
        0.3f to Color.Black.copy(alpha = 0.3f),
        0.4f to Color.Black.copy(alpha = 0.4f),
        0.5f to Color.Black.copy(alpha = 0.5f),
        0.6f to Color.Black.copy(alpha = 0.6f),
        0.7f to Color.Black.copy(alpha = 0.7f),
        0.8f to Color.Black.copy(alpha = 0.8f),
        0.9f to Color.Black.copy(alpha = 0.9f),
        1.0f to Color.Black.copy(alpha = 1f),
    )

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(200.dp, 250.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.Gray)
                .clickable {
                    onClick()
                }
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data("https://image.tmdb.org/t/p/w500/$posterPath")
                    .crossfade(true)
                    .build(),
                contentDescription = "영화 이미지",
                contentScale = ContentScale.FillBounds,
                onState = {
                    when (it) {
                        is AsyncImagePainter.State.Loading -> {
                            Log.d("MainScreen", "Loading")
                        }

                        is AsyncImagePainter.State.Success -> {
                            Log.d("MainScreen", "Success")
                        }

                        is AsyncImagePainter.State.Error -> {
                            Log.d("MainScreen", "Error")
                            Log.d("MainScreen", "${it.result.throwable}")
                        }

                        else -> {}
                    }
                },
            )

            Box(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomEnd = 12.dp, bottomStart = 12.dp))
                    .background(brush = imageBrushColor)
                    .align(Alignment.BottomStart)
            ) {
                CircleGraph(
                    modifier = Modifier.padding(start = 4.dp, top = 4.dp),
                    angle = voteAverage
                )
            }
        }

        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = movieName,
            color = Color.White,
            fontSize = 16.sp
        )
    }
}