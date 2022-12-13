package com.rengwuxian.coursecomposecustomlayout

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rengwuxian.coursecomposecustomlayout.ui.theme.CourseComposeCustomLayoutTheme
import kotlin.math.max

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CourseComposeCustomLayoutTheme {
        CustomLayout {

        }
      }
    }
  }
}

@Preview
@Composable
fun CustomLayoutPreview() {
  CustomLayout {
    Box(Modifier.size(80.dp).background(Color.Red))
    Box(Modifier.size(80.dp).background(Color.Yellow))
    Box(Modifier.size(80.dp).background(Color.Blue))
  }
}

@Composable
fun CustomLayout(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
  Layout(content, modifier) { measurables, constraints ->
    var width = 0
    var height = 0
    val placeables = measurables.map { measurable ->
      measurable.measure(constraints).also { placeable ->
        width = max(width, placeable.width)
        height += placeable.height
      }
    }
    layout(width, height) {
      var totalHeight = 0
      placeables.forEach {
        it.placeRelative(0, totalHeight)
        totalHeight += it.height
      }
    }
  }
}