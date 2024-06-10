package com.example.movie.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import com.example.movie.common.FAB
import com.example.movie.navigation.SCREEN2_ROUTE
import com.example.movie.network.model.MovieData
import com.example.movie.network.model.Result
import com.example.movie.viewmodel.TestViewModel

@Composable
fun MainScreen(
    navHostController: NavHostController,
    viewModel: TestViewModel
) {

    val movieData = viewModel.movieResponse.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
        ) {
            LazyVerticalGrid(
                modifier = Modifier,
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(movieData.value.results.size) {
                    MovieItem(
                        posterPath = movieData.value.results[it].poster_path,
                        movieName = movieData.value.results[it].title
                    )
                }
            }
        }

        FAB(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 10.dp, end = 10.dp),
            onClick = {
                navHostController.navigate(SCREEN2_ROUTE)
            }
        )
    }
}

@Composable
fun MovieItem(
    posterPath: String,
    movieName: String,
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
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(200.dp, 250.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.Gray)
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
                    .align(Alignment.BottomCenter)
            ) {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                ) {
                }
            }
        }

        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = movieName,
            color = Color.Black,
            fontSize = 16.sp
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        MainScreen(navHostController = rememberNavController(), viewModel = TestViewModel())
    }
}