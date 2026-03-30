package com.rengwuxian.coursecompose.draw

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class PacmanModifier(val mouthAngle: Float) : ModifierNodeElement<PacmanDrawNode>() {
  override fun create(): PacmanDrawNode {
    return PacmanDrawNode(mouthAngle)
  }

  override fun update(node: PacmanDrawNode) {
    node.mouthAngle = mouthAngle
  }

}

class PacmanDrawNode(var mouthAngle: Float) : Modifier.Node(), DrawModifierNode {
  override fun ContentDrawScope.draw() {
    val path = Path()
    val radius = size.minDimension / 2f
    val center = size.center
    val startAngle = mouthAngle / 2f
    val sweepAngle = 360f - mouthAngle

    path.reset()
    path.moveTo(center.x, center.y)
    path.arcTo(
      rect = Rect(
        left = center.x - radius,
        top = center.y - radius,
        right = center.x + radius,
        bottom = center.y + radius
      ),
      startAngleDegrees = startAngle,
      sweepAngleDegrees = sweepAngle,
      forceMoveTo = false // 关键：不抬笔，保证与中心点相连
    )
    path.close()

    drawPath(path = path, color = Color.Yellow)
  }

}

@Preview(backgroundColor = 0xFF000000)
@Composable
private fun DrawModifierNodeDemo() {
  val infiniteTransition = rememberInfiniteTransition(label = "PacmanTransition")
  val mouthAngle by infiniteTransition.animateFloat(
    initialValue = 2f, // 起始值：闭合留缝
    targetValue = 75f, // 目标值：最大张角
    animationSpec = infiniteRepeatable(
      animation = tween(durationMillis = 360, easing = LinearEasing),
      repeatMode = RepeatMode.Reverse // 到达目标后反向执行
    ),
    label = "MouthAngle"
  )
  Box(Modifier.padding(50.dp).then(PacmanModifier(mouthAngle)).size(200.dp)/*.drawWithCache {
    val path = Path()
    val radius = size.minDimension / 2f
    val center = size.center
    val startAngle = mouthAngle / 2f
    val sweepAngle = 360f - mouthAngle

    onDrawBehind {
      path.reset()
      path.moveTo(center.x, center.y)
      path.arcTo(
        rect = Rect(
          left = center.x - radius,
          top = center.y - radius,
          right = center.x + radius,
          bottom = center.y + radius
        ),
        startAngleDegrees = startAngle,
        sweepAngleDegrees = sweepAngle,
        forceMoveTo = false // 关键：不抬笔，保证与中心点相连
      )
      path.close()

      drawPath(path = path, color = Color.Yellow)
    }
  }*/)
}