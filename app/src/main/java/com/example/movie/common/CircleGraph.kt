package com.example.movie.common

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

@Composable
fun CircleGraph(
    modifier: Modifier = Modifier,
    angle: Double,
    textStyle: TextStyle = TextStyle(
        color = Color.White,
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold
    )
) {
    // 360도, 100% 기준으로 계산할려면 1%마다 3.6도
    val calPercentAngle = (angle * 10)
    val percentAngle = calPercentAngle * 3.6f

    val calAngleColor = when {
        calPercentAngle > 70 -> GraphHighPercentage
        calPercentAngle > 40 -> GraphMiddlePercentage
        calPercentAngle > 1 -> GraphLowPercentage
        else -> GraphNonePercentage
    }

    val boxBackgroundColor = when {
        calPercentAngle > 70 -> GraphHighBackground
        calPercentAngle > 40 -> GraphMiddleBackground
        calPercentAngle > 1 -> GraphLowBackground
        else -> GraphNonePercentage
    }

    val textCalPercentAngle = if (calPercentAngle == 0.0) {
        "NR"
    } else {
        calPercentAngle.roundToInt().toString() + "%"
    }

    Box(
        modifier = modifier
            .size(30.dp)
            .border(1.dp, CircleGraphPoint, CircleShape)
            .background(boxBackgroundColor, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawIntoCanvas {

                val borderWidth = 1.dp.toPx()
                val canvasSize = drawContext.size
                val strokeSize = 4f
                val adjustment =
                    borderWidth + strokeSize / 2 // stroke는 path의 중심을 따라(즉 border의 중심을 따라)그려지기 때문에 중심을 기준으로 반씩 그려지므로 반만큼 빼줘야함
                val adjustedSize = Size(
                    canvasSize.width - 2 * adjustment,
                    canvasSize.height - 2 * adjustment
                ) // 2를 곱하는 이유는 양쪽에 stroke가 그려지기 때문이다 즉 width 기준으로는 좌우, height 기준으로는 상하에 stroke가 그려진다.
                val drawCircleCenter = Offset(canvasSize.width / 2, canvasSize.height / 2)

                drawArc(
                    color = calAngleColor,
                    startAngle = -90f,
                    sweepAngle = percentAngle.toFloat(),
                    useCenter = false,
                    topLeft = Offset(adjustment, adjustment),
                    size = adjustedSize,
                    style = Stroke(width = strokeSize, cap = StrokeCap.Round)
                )

                val circleRadius = minOf(canvasSize.width, canvasSize.height) / 2 - strokeSize * 2

                drawCircle(
                    color = CircleGraphPoint,
                    radius = circleRadius,
                    center = drawCircleCenter
                )
            }
        }

        Text(
            text = textCalPercentAngle,
            style = textStyle
        )
    }
}
