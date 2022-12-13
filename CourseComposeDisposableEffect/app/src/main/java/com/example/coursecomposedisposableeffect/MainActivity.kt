package com.example.coursecomposedisposableeffect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.coursecomposedisposableeffect.ui.theme.CourseComposeDisposableEffectTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CourseComposeDisposableEffectTheme {
        var showText by remember { mutableStateOf(false) }
        Button(onClick = { showText = !showText }) {
          Text("点击")
          if (showText) {
            Text("rengwuxian")
          }
          SideEffect {
            println("@@@ SideEffect()")
          }
          DisposableEffect(showText) {
            println("@@@ 进入界面啦！")
            onDispose {
              println("@@@ 离开界面啦！")
            }
          }
        }
      }
    }
  }
}