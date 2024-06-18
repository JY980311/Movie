package com.example.movie.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.movie.common.FAB
import com.example.movie.common.MovieItem
import com.example.movie.navigation.DETAILSCRREN_ROUTE
import com.example.movie.navigation.COMMUNITYSCREEN_ROUTE
import com.example.movie.ui.theme.MainColor
import com.example.movie.viewmodel.TestViewModel

@Composable
fun MainScreen(
    navHostController: NavHostController,
    viewModel: TestViewModel
) {
    val movieData = viewModel.movieResponse.collectAsStateWithLifecycle()

    Box(modifier = Modifier
        .fillMaxSize()
        .background(MainColor)
    ) {
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
                        movieName = movieData.value.results[it].title,
                        voteAverage = movieData.value.results[it].vote_average,
                        onClick = {
                            viewModel.getSelectedMovieIndex(it)
                            viewModel.getImageData()
                            viewModel.getCreditData()
                            viewModel.getGenresData()
                            navHostController.navigate(DETAILSCRREN_ROUTE)
                        }
                    )
                }
            }
        }

        FAB(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 10.dp, end = 10.dp),
            onClick = {
                navHostController.navigate(COMMUNITYSCREEN_ROUTE)
            }
        )
    }
}

val images = listOf(
    "https://w0.peakpx.com/wallpaper/324/121/HD-wallpaper-new-2023-movie-john-wick-4-poster-movie.jpg",
    "https://w0.peakpx.com/wallpaper/694/975/HD-wallpaper-oppenheimer-movie-2023.jpg",
    "https://w0.peakpx.com/wallpaper/640/109/HD-wallpaper-transformers-rise-of-the-beasts-movie-poster-2023.jpg",
    "https://w0.peakpx.com/wallpaper/542/826/HD-wallpaper-optimus-prime-dragon-movie-poster-transformers-transformers-5-transformers-last-knight.jpg"
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        MainScreen(navHostController = rememberNavController(), viewModel = TestViewModel())
    }
    ///ActorCarouseSlider(images = images)
}