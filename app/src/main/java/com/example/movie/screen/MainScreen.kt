package com.example.movie.screen

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import com.example.movie.common.FAB
import com.example.movie.navigation.SCREEN2_ROUTE
import com.example.movie.viewmodel.TestViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
                        movieName = movieData.value.results[it].title,
                        voteAverage = movieData.value.results[it].vote_average
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

//TODO: 컴포넌트로 빼기
@Composable
fun MovieItem(
    posterPath: String,
    movieName: String,
    voteAverage: Double = 0.0,
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
                    .align(Alignment.BottomStart)
            ) {
                Test(
                    modifier = Modifier.padding(start = 4.dp, top = 4.dp),
                    angle = voteAverage
                )
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

//TODO: Screen으로 빼기
@Composable
fun DetailScreen() {
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
            model = "https://image.tmdb.org/t/p/w500/plNOSbqkSuGEK2i15A5btAXtB7t.jpg",
            contentDescription = "샘플사진",
            alpha = 0.5f
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
                    text = "영화 제목",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                )

                CarouseSlider(images = images)
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
                        Test(
                            modifier = Modifier.size(50.dp),
                            angle = 6.0,
                            textStyle = TextStyle(
                                color = Color.White,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    Text(
                        text = "투표 인원 : ${100}",
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
                    text = "진화한 유인원과 퇴화된 인간들이 살아가는 땅. 유인원 리더 프록시무스는 완전한 군림을 위해 인간들을 사냥하며 자신의 제국을 건설한다. 한편, 또 다른 유인원 노아는 우연히 숨겨진 과거의 이야기와 시저의 가르침을 듣게 되고 인간과 유인원이 함께 할 새로운 세상을 꿈꾼다. 어느 날 그의 앞에 나타난 의문의 한 인간 소녀. 노아는 그녀와 함께 자유를 향한 여정을 시작하게 되는데…\",",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    )
                )

                ActorCarouseSlider(
                    images = images
                )
            }
        }
    }
}


val images = listOf(
    "https://w0.peakpx.com/wallpaper/324/121/HD-wallpaper-new-2023-movie-john-wick-4-poster-movie.jpg",
    "https://w0.peakpx.com/wallpaper/694/975/HD-wallpaper-oppenheimer-movie-2023.jpg",
    "https://w0.peakpx.com/wallpaper/640/109/HD-wallpaper-transformers-rise-of-the-beasts-movie-poster-2023.jpg",
    "https://w0.peakpx.com/wallpaper/542/826/HD-wallpaper-optimus-prime-dragon-movie-poster-transformers-transformers-5-transformers-last-knight.jpg"
)

//TODO: 컴포넌트로 빼기
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarouseSlider(
    modifier: Modifier = Modifier,
    images: List<String>
) {

    val pagerState = rememberPagerState(pageCount = { images.size }, initialPageOffsetFraction = 0f)
    val coroutineScope = rememberCoroutineScope()

    /*LaunchedEffect(key1 = pagerState) {
        coroutineScope.launch {
            while (true) {
                delay(2000)
                val targetPage = if (pagerState.currentPage == images.size - 1) 0 else pagerState.currentPage + 1
                pagerState.animateScrollToPage(targetPage)
            }
        }
    }*/

    HorizontalPager(
        modifier = modifier.wrapContentSize(),
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 100.dp),
        pageSpacing = 10.dp
    ) { page ->
        Card(
            modifier = Modifier
                .height(300.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            AsyncImage(
                modifier = Modifier.width(200.dp),
                model = images[page],
                contentDescription = "Image",
                contentScale = ContentScale.FillBounds,
            )
        }
    }
}

//TODO: 컴포넌트로 빼기
@Composable
fun ActorCarouseSlider(
    modifier: Modifier = Modifier,
    images: List<String>
) {
    LazyRow(
        modifier = modifier
            .wrapContentSize(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(images.size) { page ->
            Column(
                modifier = modifier.width(100.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Card(
                    modifier = Modifier
                        .size(100.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    AsyncImage(
                        modifier = Modifier.fillMaxSize(),
                        model = images[page],
                        contentDescription = "Image",
                        contentScale = ContentScale.FillBounds,
                    )
                }

                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "배우 이름",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                )

            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    /*Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        MainScreen(navHostController = rememberNavController(), viewModel = TestViewModel())
    }*/
    DetailScreen()
    ///ActorCarouseSlider(images = images)
}