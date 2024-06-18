package com.example.movie.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movie.R
import com.example.movie.common.ChatBox
import com.example.movie.ui.theme.MainColor
import com.example.movie.viewmodel.PostViewModel

@Composable
fun CommunityScreen(
    navHostController: NavController,
    viewModel: PostViewModel
) {
    val postData = viewModel.postResponse.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainColor)
    ) {
        IconButton(
            modifier = Modifier.align(Alignment.End),
            onClick = {
                navHostController.navigate("write")
            }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "더하기",
                tint = Color.White
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(13.dp)
        ) {
            items(postData.value.size) {
                val reversedIndex = postData.value.size - 1 - it // 역순으로 인덱스 배치
                ChatBox(
                    title = postData.value[reversedIndex].title,
                    content = postData.value[reversedIndex].content,
                    time = viewModel.getFormattedTimeDifference(postData.value[reversedIndex].created_at)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen2() {
    CommunityScreen(
        navHostController = rememberNavController(),
        viewModel = PostViewModel()
    )
}