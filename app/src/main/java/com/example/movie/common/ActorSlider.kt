package com.example.movie.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.movie.R


@Composable
fun ActorSlider(
    modifier: Modifier = Modifier,
    images: List<String>,
    names: List<String>
) {
    LazyRow(
        modifier = modifier.wrapContentSize(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(images.size) { page ->
            Column(
                modifier = modifier.width(100.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Card(
                    modifier = Modifier.size(100.dp), elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    AsyncImage(
                        modifier = Modifier.fillMaxSize(),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(images[page])
                            .error(R.drawable.empty_profile)
                            .build(),
                        contentDescription = "Image",
                        contentScale = ContentScale.FillBounds,
                    )
                }

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = names[page],
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