package com.rengwuxian.coursecompose.draw

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
private fun DrawDemo() {
  Text("rengwuxian.com\n扔物线", Modifier
    .drawWithCache {
      val brush = Brush.linearGradient(listOf(Color.Red, Color.Yellow))
      onDrawBehind {
        drawRect(brush)
      }
    }
    .padding(24.dp)
    .drawWithContent {
      drawContent()
      drawLine(
        Color.White, Offset(0f, 0f), Offset(size.width, size.height),
        strokeWidth = 10.dp.toPx()
      )

    }, fontSize = 24.sp)
}

















