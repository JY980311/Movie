package com.example.movie.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movie.common.ChatBox
import com.example.movie.ui.theme.MainColor
import com.example.movie.viewmodel.PostViewModel

@Composable
fun CommunityScreen(
    viewModel: PostViewModel
) {

    val postData = viewModel.postResponse.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainColor)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(13.dp)
        ) {
            items(postData.value.size) {
                postData.value[it].created_at?.let { it1 ->
                    ChatBox(
                        title = postData.value[it].title,
                        content = postData.value[it].content,
                        time = it1
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen2() {
    CommunityScreen(viewModel = PostViewModel())
}