package com.rengwuxian.coursecomposeanimatable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    var big by mutableStateOf(false)

    setContent {
      val size by animateDpAsState(if (big) 96.dp else 48.dp) // State
      val size1 = remember(big) { if (big) 96.dp else 48.dp }
      val anim = remember { Animatable(size1, Dp.VectorConverter) }
      LaunchedEffect(big) {
        anim.snapTo(if (big) 192.dp else 0.dp)
        anim.animateTo(size1)
      }
      Box(
        Modifier
          .size(anim.value)
          .background(Color.Green)
          .clickable {
            big = !big
          })
    }
  }
}