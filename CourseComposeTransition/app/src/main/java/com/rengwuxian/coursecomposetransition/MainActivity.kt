package com.rengwuxian.coursecomposetransition

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rengwuxian.coursecomposetransition.ui.theme.CourseComposeTransitionTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CourseComposeTransitionTheme {
        TransitionSquare()
      }
    }
  }
}

@Preview
@Composable
private fun TransitionSquare() {
  var big by remember { mutableStateOf(false) }
  val bigTransition = updateTransition(big, "big")
  val size by bigTransition.animateDp(label = "size") { if (it) 96.dp else 48.dp }
  val corner by bigTransition.animateDp(label = "corner") { if (it) 0.dp else 18.dp }
  Box(
    Modifier
      .size(size)
      .clip(RoundedCornerShape(corner))
      .background(Color.Green)
      .clickable {
        big = !big
      })
}