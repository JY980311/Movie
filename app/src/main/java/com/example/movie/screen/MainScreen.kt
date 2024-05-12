package com.example.movie.screen

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.movie.common.FAB
import com.example.movie.navigation2.BarItem
import com.example.movie.navigation2.NavBarItems
import com.example.movie.navigation2.NavRoutes

@Composable
fun MainScreen(navHostController: NavHostController) {
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
                items(10) {
                    MovieItem()
                }
            }
        }

        FAB(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 10.dp, end = 10.dp),
            onClick = {
                navHostController.navigate(NavRoutes.Screen2.route)
            }
        )
    }
}

@Composable
fun MovieItem() {
    Box(
        modifier = Modifier
            .size(120.dp, 200.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.Gray)
    ) {
        Text(text = "영화 이미지", modifier = Modifier.align(Alignment.Center))
        Box(
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomEnd = 12.dp, bottomStart = 12.dp))
                .border(1.dp, Color.Red, RoundedCornerShape(bottomEnd = 12.dp, bottomStart = 12.dp))
                .align(Alignment.BottomCenter)
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center),
            ) {
                Text(text = "영화 제목")
                Text(text = "평점")
            }
        }
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
    }
}