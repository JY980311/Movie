package com.example.movie.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movie.ui.theme.CircleGraphPoint
import com.example.movie.ui.theme.GraphHighBackground
import com.example.movie.ui.theme.GraphHighPercentage
import com.example.movie.ui.theme.GraphLowBackground
import com.example.movie.ui.theme.GraphLowPercentage
import com.example.movie.ui.theme.GraphMiddleBackground
import com.example.movie.ui.theme.GraphMiddlePercentage
import com.example.movie.ui.theme.GraphNonePercentage
import kotlin.math.roundToInt

//TODO: 컴포넌트로 빼기

/*
@Preview(showBackground = true)
@Composable
fun TestPreview() {
    Test(modifier = Modifier,3.0)
}*/
