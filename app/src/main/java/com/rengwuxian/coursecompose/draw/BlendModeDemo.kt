package com.rengwuxian.coursecompose.draw

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
private fun BlendModeDemo() {
  Canvas(Modifier.size(150.dp)
    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)) {
    drawCircle(Color.Red, size.width / 3, Offset(size.width / 3f, size.height / 3f))
    drawCircle(Color.Blue, size.width / 3, Offset(size.width / 3f * 2, size.height / 3f * 2),
      blendMode = BlendMode.SrcOut)
  }
}









