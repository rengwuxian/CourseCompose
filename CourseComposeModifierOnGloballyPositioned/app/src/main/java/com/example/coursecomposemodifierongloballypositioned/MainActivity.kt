package com.example.coursecomposemodifierongloballypositioned

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.*
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.coursecomposemodifierongloballypositioned.ui.theme.CourseComposeModifierOnGloballyPositionedTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CourseComposeModifierOnGloballyPositionedTheme {
        var offsetX by remember { mutableStateOf(0) }
        var offsetY by remember { mutableStateOf(0) }
        Modifier
          .onPlaced {
            val posInParent = it.positionInParent()
            offsetX = posInParent.x.toInt()
            offsetY = posInParent.y.toInt()
          }
          .offset {
            IntOffset(offsetX, offsetY)
          }
          .size(40.dp)
        Modifier.onGloballyPositioned {
          it.positionInParent()
        }
      }
    }
  }
}