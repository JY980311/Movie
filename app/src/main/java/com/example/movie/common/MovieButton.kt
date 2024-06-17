package com.example.movie.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movie.ui.theme.ButtonColor

@Composable
fun MovieButton(
    modifier:Modifier = Modifier,
    text: String = "Button",
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = ButtonColor,
            contentColor = Color.Black
        ),
        onClick = { onClick() }
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 20.sp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainButtonPreview() {
    MovieButton( text = "Button", onClick = {})
}