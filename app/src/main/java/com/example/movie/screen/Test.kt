package com.example.movie.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.movie.viewmodel.TestViewModel

@Composable
fun Test(viewModel: TestViewModel) {

    val movieData =  viewModel.movieResponse.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = {
            viewModel.getMovieData()
        }) {
           Text(text = "영화 정보 가져오기")
        }
        Text(text = movieData.value.toString())
    }
}

@Preview(showBackground = true)
@Composable
fun TestPreview() {
    Test(TestViewModel())
}