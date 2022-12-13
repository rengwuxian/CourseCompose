package com.rengwuxian.coursecomposemodifierthen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.CombinedModifier
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rengwuxian.coursecomposemodifierthen.ui.theme.CourseComposeModifierThenTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CourseComposeModifierThenTheme {
        Modifier.background(Color.Blue)
          .then(Modifier.padding(8.dp))
          .then(Modifier.size(80.dp))
        CombinedModifier(
          CombinedModifier(Modifier.background(Color.Blue), Modifier.padding(8.dp)),
          Modifier.size(80.dp))
      }
    }
  }
}