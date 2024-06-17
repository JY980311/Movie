package com.example.movie.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movie.ui.theme.MainColor

@Composable
fun MovieTextField(
    modifier: Modifier = Modifier,
    title: String,
    text: String,
    onValueChange: (String) -> Unit,
    focusManager: FocusManager
) {
    Column {
        Text(modifier = Modifier
            .padding(start = 8.dp,bottom = 4.dp),
            text= title,
            style = TextStyle(
                color = Color.White,
                fontSize = 20.sp
            )
        )

        BasicTextField(
            modifier = modifier
                .fillMaxWidth()
                .height(100.dp),
            value = text,
            onValueChange = { onValueChange(it) },
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 20.sp
            ),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        ) { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .border(1.dp, Color.White, RoundedCornerShape(12.dp))
                    .background(Color.Transparent)
                    .padding(8.dp)
            ){
                innerTextField()
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF1C1C1C)
@Composable
fun PreviewMovieTextField() {
    var text by remember {
        mutableStateOf("")
    }

    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainColor)
            .padding(16.dp)
    ){
        MovieTextField(
            modifier = Modifier.height(50.dp),
            title = "제목",
            text = text,
            onValueChange = { text = it },
            focusManager = focusManager
        )
    }
}