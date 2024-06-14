package com.example.movie.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import com.example.movie.common.ActorSlider
import com.example.movie.common.CarouseSlider
import com.example.movie.common.Chip
import com.example.movie.common.CircleGraph
import com.example.movie.viewmodel.TestViewModel

//TODO: Screen으로 빼기, 디테일 스크린
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun DetailScreen(
    viewModel: TestViewModel
) {
    val selectedIndex = viewModel.selectedMovieIndex.collectAsStateWithLifecycle()
    val movieData = viewModel.movieResponse.collectAsStateWithLifecycle()
    val imageData = viewModel.imageResponse.collectAsStateWithLifecycle()
    val castData = viewModel.castResponse.collectAsStateWithLifecycle()
    val genreData = viewModel.genresResponse.collectAsStateWithLifecycle()

    val selectedMovie = movieData.value.results[selectedIndex.value]

    Log.d("DetailScreen", "영화 리스트 갯수: ${movieData.value.results.size}")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(
                    scaleX = 1.5f, // 확대 비율 설정 (1.5배 확대)
                    scaleY = 1.5f,
                ),
            model = ImageRequest
                .Builder(LocalContext.current)
                .data("https://image.tmdb.org/t/p/w500/${selectedMovie.poster_path}")
                .crossfade(true)
                .build(),
            contentDescription = "영화 이미지",
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
            alpha = 0.3f
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    text = selectedMovie.title,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                )

                CarouseSlider(images = imageData.value.backdrops.map { // 리스트의 각 요소에 대해 반복, 변환된 URL을 모아서 새로운 리스트로 만든다.
                    "https://image.tmdb.org/t/p/w500/${it.file_path}"
                })

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ){
                    items(selectedMovie.genre_ids.size) {
                        Chip(text = genreData.value.genres[it].name)
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            text = "종합 평점 : ",
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        CircleGraph(
                            modifier = Modifier.size(50.dp),
                            angle = selectedMovie.vote_average,
                            textStyle = TextStyle(
                                color = Color.White,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    Text(
                        text = "투표 인원 : ${selectedMovie.vote_count}",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = selectedMovie.overview,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    )
                )

                ActorSlider(
                    images = castData.value.cast.map {
                        "https://image.tmdb.org/t/p/w500/${it.profile_path}"
                    },
                    names = castData.value.cast.map {
                        it.name
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewDetailScreen() {
    DetailScreen(viewModel = TestViewModel())
}