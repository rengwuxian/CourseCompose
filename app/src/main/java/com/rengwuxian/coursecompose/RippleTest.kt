package com.rengwuxian.coursecompose

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

object ScaleIndication : IndicationNodeFactory {
  override fun create(interactionSource: InteractionSource): DelegatableNode {
    return ScaleIndicationNode(interactionSource)
  }

  override fun hashCode(): Int = -1
  override fun equals(other: Any?) = other === this
}

private class ScaleIndicationNode(private val interactionSource: InteractionSource) :
  Modifier.Node(), DrawModifierNode {

  private val scaleAnim = Animatable(1f)

  override fun onAttach() {
    coroutineScope.launch {
      interactionSource.interactions.collectLatest { interaction ->
        when (interaction) {
          is PressInteraction.Press -> {
            scaleAnim.animateTo(targetValue = 1.1f)
          }

          is PressInteraction.Release, is PressInteraction.Cancel -> {
            scaleAnim.animateTo(targetValue = 1f)
          }
        }
      }
    }
  }

  override fun ContentDrawScope.draw() {
    scale(scaleAnim.value) { this@draw.drawContent() }
  }
}

@Preview(showBackground = true)
@Composable
fun ScaleIndicationDemo() {
  MaterialTheme {
    Column(modifier = Modifier.padding(8  .dp)) {
      Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Box(
          modifier =
            Modifier
              .padding(8.dp)
              .size(150.dp)
              .clickable { println("Duang!") }
              .background(
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.medium
              ),
          contentAlignment = Alignment.Center
        ) { Text("Ripple", color = MaterialTheme.colorScheme.onPrimary, fontSize = 22.sp) }

        Box(
          modifier =
            Modifier
              .padding(8.dp)
              .size(150.dp)
              .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(color = Color.Green)
              ) { println("Duang!") }
              .background(
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.medium
              ),
          contentAlignment = Alignment.Center
        ) { Text("Green Ripple", color = MaterialTheme.colorScheme.onTertiary, fontSize = 22.sp) }
      }
      Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Box(
          modifier =
            Modifier
              .padding(8.dp)
              .size(150.dp)
              .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ScaleIndication
              ) { println("Duang!") }
              .background(
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.medium
              ),
          contentAlignment = Alignment.Center
        ) { Text("点我放大", color = MaterialTheme.colorScheme.onPrimary, fontSize = 22.sp) }

        Box( // Added Shrink Button
          modifier =
            Modifier
              .padding(8.dp)
              .size(150.dp)
              .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication =
                  AvatarIndication(
                    painterResource(id = R.drawable.rengwuxian),
                    MaterialTheme.shapes.medium
                  )
              ) { println("Duang!") }
              .background(
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.medium
              ),
          contentAlignment = Alignment.Center
        ) { Text("点我显示头像", color = MaterialTheme.colorScheme.onTertiary, fontSize = 22.sp) }
      }
    }
  }
}

class AvatarIndication(private val painter: Painter, private val shape: Shape = RectangleShape) :
  IndicationNodeFactory {
  override fun create(interactionSource: InteractionSource): DelegatableNode {
    return AvatarIndicationNode(interactionSource, painter, shape)
  }

  override fun hashCode(): Int {
    var result = painter.hashCode()
    result = 31 * result + shape.hashCode()
    return result
  }

  override fun equals(other: Any?) =
    other is AvatarIndication && other.painter == painter && other.shape == shape
}

private class AvatarIndicationNode(
  private val interactionSource: InteractionSource,
  private val painter: Painter,
  private val shape: Shape,
) : Modifier.Node(), DrawModifierNode {

  private val alphaAnim = Animatable(0f)

  override fun onAttach() {
    coroutineScope.launch {
      interactionSource.interactions.collectLatest { interaction ->
        when (interaction) {
          is PressInteraction.Press -> {
            alphaAnim.animateTo(targetValue = 0.5f)
          }

          is PressInteraction.Release, is PressInteraction.Cancel -> {
            alphaAnim.animateTo(targetValue = 0f)
          }
        }
      }
    }
  }

  override fun ContentDrawScope.draw() {
    drawContent()

    if (alphaAnim.value > 0f) {
      drawIntoCanvas { canvas ->
        val outline = shape.createOutline(size, layoutDirection, this@draw)
        canvas.save()
        when (outline) {
          is Outline.Rectangle -> canvas.clipRect(outline.rect)
          is Outline.Rounded -> canvas.clipPath(Path().apply { addRoundRect(outline.roundRect) })
          is Outline.Generic -> canvas.clipPath(outline.path)
        }
        with(painter) { draw(size = this@draw.size, alpha = alphaAnim.value) }
        canvas.restore()
      }
    }
  }
}
