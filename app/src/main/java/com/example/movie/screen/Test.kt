package com.example.movie.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.movie.viewmodel.TestViewModel

@Composable
fun Test(angle: Float) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .border(1.dp, Color.Black, CircleShape)
            .background(Color.Cyan, CircleShape)
        ,
    ){
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawIntoCanvas {

                val borderWidth = 1.dp.toPx()
                val canvasSize = drawContext.size
                val strokeSize = 10f
                val adjustment = borderWidth + strokeSize / 2
                val adjustedSize = Size(canvasSize.width - 2 * adjustment, canvasSize.height - 2 * adjustment)

                drawArc(
                    color = Color.Red,
                    startAngle = -90f,
                    sweepAngle = angle,
                    useCenter= false,
                    topLeft = Offset(adjustment, adjustment),
                    size = adjustedSize,
                    style = Stroke(width = strokeSize, cap = StrokeCap.Round)
                )

                drawCircle(
                    color = Color.Yellow,
                    radius = 40f,
                    center = Offset(canvasSize.width/2, canvasSize.height/2)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestPreview() {
    Test(90f)
}