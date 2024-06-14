package com.example.movie.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

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
                .height(200.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            AsyncImage(
                modifier = Modifier.width(300.dp),
                model = images[page],
                contentDescription = "Image",
                contentScale = ContentScale.FillBounds,
            )
        }
    }
}