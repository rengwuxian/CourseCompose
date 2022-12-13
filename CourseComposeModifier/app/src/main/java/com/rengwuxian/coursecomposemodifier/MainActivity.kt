package com.rengwuxian.coursecomposemodifier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rengwuxian.coursecomposemodifier.ui.theme.CourseComposeModifierTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CourseComposeModifierTheme {
        Modifier // 伴生对象
        Custom(Modifier.size(80.dp))
        Custom()
        Modifier.padding(8.dp).background(Color.Blue)
      }
    }
  }
}

@Composable
fun Custom(modifier: Modifier = Modifier) {
  Box(modifier.background(Color.Blue)) {}
}