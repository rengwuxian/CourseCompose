package com.example.coursecomposemodifiersemantics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.semantics.SemanticsModifier
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.coursecomposemodifiersemantics.ui.theme.CourseComposeModifierSemanticsTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CourseComposeModifierSemanticsTheme {
        Column {
          Text("床前明月光")
          Box(
            Modifier.width(100.dp).height(60.dp).background(Color.Magenta).clearAndSetSemantics {
                contentDescription = "大方块"
              }) {
            Text("小方块")
          }
          Button(onClick = { /*TODO*/ }) {
            Text("疑是地上霜", Modifier.semantics(true) {  })
          }
        }
      }
    }
  }
}