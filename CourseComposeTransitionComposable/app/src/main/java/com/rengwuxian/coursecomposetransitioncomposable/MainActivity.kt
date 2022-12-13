package com.rengwuxian.coursecomposetransitioncomposable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rengwuxian.coursecomposetransitioncomposable.ui.theme.CourseComposeTransitionComposableTheme

class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CourseComposeTransitionComposableTheme {
        AC()
      }
    }
  }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
private fun AC() {
  Column {
    var shown by remember { mutableStateOf(true) }
    AnimatedContent(shown, transitionSpec = {
      if (targetState) {
        (fadeIn(tween(3000)) with
            fadeOut(tween(3000, 3000))).apply {
          targetContentZIndex = -1f
        }
      } else {
        (fadeIn(tween(3000)) with fadeOut(tween(3000, 3000)) using
            SizeTransform())

      }
       }) {
      if (it) {
        TransitionSquare()
      } else {
        Box(Modifier.size(24.dp).background(Color.Red))
      }
    }
    Button(onClick = { shown = !shown }) {
      Text("切换")
    }
  }
}

@Composable
private fun CF() {
  Column {
    var shown by remember { mutableStateOf(true) }
    Crossfade(shown) {
      if (it) {
        TransitionSquare()
      } else {
        Box(Modifier.size(24.dp).background(Color.Red))
      }
    }
    Button(onClick = { shown = !shown }) {
      Text("切换")
    }
  }
}

@Composable
private fun AV() {
  Column {
    var shown by remember { mutableStateOf(true) }
    AnimatedVisibility(shown, enter = fadeIn() + expandIn(), exit = fadeOut() + shrinkOut()) {
      TransitionSquare()
    }
    Button(onClick = { shown = !shown }) {
      Text("切换")
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