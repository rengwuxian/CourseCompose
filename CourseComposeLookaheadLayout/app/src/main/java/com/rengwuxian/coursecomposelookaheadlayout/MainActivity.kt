package com.rengwuxian.coursecomposelookaheadlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.movableContentWithReceiverOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.LookaheadLayout
import androidx.compose.ui.layout.LookaheadLayoutScope
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.rengwuxian.coursecomposelookaheadlayout.ui.theme.CourseComposeLookaheadLayoutTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
  @OptIn(ExperimentalComposeUiApi::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CourseComposeLookaheadLayoutTheme {
        CustomLookaheadLayout()
      }
    }
  }
}

@Composable
@OptIn(ExperimentalComposeUiApi::class)
private fun CustomLookaheadLayout() {
  var isTextHeight200Dp by remember { mutableStateOf(false) }
  var textHeightPx by remember { mutableStateOf(0) }
  val textHeightPxAnim by animateIntAsState(textHeightPx)
  var lookaheadOffset by remember { mutableStateOf(Offset.Zero) }
  val lookaheadOffsetAnim by animateOffsetAsState(lookaheadOffset)
  val sharedText = remember {
    movableContentWithReceiverOf<LookaheadLayoutScope> {
      Text("扔物线",
        Modifier
          .then(if (isTextHeight200Dp) Modifier.padding(50.dp) else Modifier)
          .onPlaced { lookaheadScopeCoordinates, layoutCoordinates ->
            lookaheadOffset = lookaheadScopeCoordinates.localLookaheadPositionOf(layoutCoordinates)
          }
          .intermediateLayout { measurable, constraints, lookaheadSize ->
            textHeightPx = lookaheadSize.height
            val placeable = measurable.measure(
              Constraints.fixed(lookaheadSize.width, textHeightPxAnim)
            )
            layout(placeable.width, placeable.height) {
              placeable.placeRelative(
                (lookaheadOffsetAnim - lookaheadOffset).x.roundToInt(),
                (lookaheadOffsetAnim - lookaheadOffset).y.roundToInt()
              )
            }
          }
          .then(if (isTextHeight200Dp) Modifier.height(200.dp) else Modifier)
          .clickable {
            isTextHeight200Dp = !isTextHeight200Dp
          })
    }
  }
  SimpleLookaheadLayout {
    if (isTextHeight200Dp) {
      sharedText()
    } else {
      Column {
        sharedText()
        Text("rengwuxian")
      }
    }
  }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SimpleLookaheadLayout(content: @Composable LookaheadLayoutScope.() -> Unit) {
  LookaheadLayout(content) { measurables, constraints ->
    val placeables = measurables.map { it.measure(constraints) }
    val width = placeables.maxOf { it.width }
    val height = placeables.maxOf { it.height }
    layout(width, height)  {
      placeables.forEach { it.placeRelative(0, 0) }
    }
  }
}