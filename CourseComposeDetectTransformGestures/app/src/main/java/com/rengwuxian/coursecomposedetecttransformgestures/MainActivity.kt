package com.rengwuxian.coursecomposedetecttransformgestures

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import com.rengwuxian.coursecomposedetecttransformgestures.ui.theme.CourseComposeDetectTransformGesturesTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CourseComposeDetectTransformGesturesTheme {
        Modifier.pointerInput(Unit) {
          detectTransformGestures(true) { centroid, pan, zoom, rotation ->  }
        }
      }
    }
  }
}