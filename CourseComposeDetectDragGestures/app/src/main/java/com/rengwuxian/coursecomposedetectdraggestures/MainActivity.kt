package com.rengwuxian.coursecomposedetectdraggestures

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.DraggableState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import com.rengwuxian.coursecomposedetectdraggestures.ui.theme.CourseComposeDetectDragGesturesTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CourseComposeDetectDragGesturesTheme {
        Modifier.pointerInput(Unit) {
          detectDragGestures { change, dragAmount ->

          }
        }
        val draggableState = rememberDraggableState {

        }
        Modifier.draggable(draggableState, Orientation.Horizontal)
        LaunchedEffect(Unit) {
          draggableState.drag {
            dragBy(100f)
          }
        }
      }
    }
  }
}