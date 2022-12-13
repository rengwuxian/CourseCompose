package com.rengwuxian.coursecomposeanimateasstate

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.ViewPropertyAnimator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    var big by mutableStateOf(false)

    setContent {
      val size by animateDpAsState(if (big) 96.dp else 48.dp) // State
      Box(
        Modifier
          .size(size)
          .background(Color.Green)
          .clickable {
            big = !big
          })
    }
  }
}