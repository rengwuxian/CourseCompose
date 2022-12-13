package com.rengwuxian.coursecomposemodifierpointerinput

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerInputModifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.SemanticsModifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.GestureDetectorCompat
import com.rengwuxian.coursecomposemodifierpointerinput.ui.theme.CourseComposeModifierPointerInputTheme

class MainActivity : ComponentActivity() {
  @OptIn(ExperimentalFoundationApi::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CourseComposeModifierPointerInputTheme {
        Modifier.clickable {  }
        Box(Modifier.size(48.dp)
          .background(Color.Red)
          .combinedClickable(onLongClick = {
          println("combinedClickable: onLongClick")
        }, onDoubleClick = {
            println("combinedClickable: onDoubleClick")
          }) { println("combinedClickable: onClick") })

        Modifier.pointerInput(Unit) {
          detectTapGestures(onTap = {}, onDoubleTap = {}, onLongPress = {}, onPress = {})
          forEachGesture {
            awaitPointerEventScope {
              val down = awaitFirstDown()
              // 监听抬起
            }
          }
        }
        SemanticsModifier
        Modifier.pointerInput().pointerInput().size(??)
        // click vs tap
      }
    }
  }
}