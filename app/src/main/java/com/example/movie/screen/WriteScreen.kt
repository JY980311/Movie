package com.example.movie.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movie.common.MovieButton
import com.example.movie.common.MovieTextField
import com.example.movie.ui.theme.MainColor
import com.example.movie.viewmodel.PostViewModel
import com.example.movie.viewmodel.State

@Composable
fun WriteScreen(
    viewModel: PostViewModel
) {

    val state = viewModel.state.collectAsState()
    val sendPostData = viewModel.sendPostData.collectAsState()
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "글 작성하기",
            style = TextStyle(
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        MovieTextField(
            modifier = Modifier.height(45.dp),
            title = "제목",
            text = sendPostData.value.title,
            onValueChange = {
                viewModel.inputTitle(it)
            },
            focusManager = focusManager
        )

        Spacer(modifier = Modifier.height(16.dp))

        MovieTextField(
            modifier = Modifier.height(250.dp),
            title = "내용",
            text = sendPostData.value.content,
            onValueChange = {
                viewModel.inputContent(it)
            },
            focusManager = focusManager
        )
        
        Spacer(modifier = Modifier.weight(1f))

        MovieButton(
            modifier = Modifier.padding(bottom = 25.dp),
            loading = state.value == State.LOADING,
        ) {
            //TODO: 글 보내기
            viewModel.createPostData(viewModel.sendPostData.value)
        }
    }
}
@Preview(showBackground =true)
@Composable
fun PreviewWriteScreen() {
    WriteScreen(
        viewModel = PostViewModel()
    )
}