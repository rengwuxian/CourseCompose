package com.rengwuxian.coursecomposeanimatableblock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rengwuxian.coursecomposeanimatableblock.ui.theme.CourseComposeAnimatableBlockTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CourseComposeAnimatableBlockTheme {
        val anim = remember { Animatable(0.dp, Dp.VectorConverter) }
        var padding2 by remember { mutableStateOf(anim.value) }
        val decay = remember { exponentialDecay<Dp>() }
        LaunchedEffect(Unit) {
          delay(1000)
          anim.animateDecay(1000.dp, decay) {
            padding2 = value
          }
        }
        Row {
          Box(
            Modifier
              .padding(0.dp, anim.value, 0.dp, 0.dp)
              .size(100.dp)
              .background(Color.Green)
          )
          Box(
            Modifier
              .padding(0.dp, padding2, 0.dp, 0.dp)
              .size(100.dp)
              .background(Color.Red)
          )
        }
      }
    }
  }
}