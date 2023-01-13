package com.rengwuxian.coursecomposesubcomposelayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.RootMeasurePolicy.measure
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import com.rengwuxian.coursecomposesubcomposelayout.ui.theme.CourseComposeSubcomposeLayoutTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CourseComposeSubcomposeLayoutTheme {
        SubcomposeLayout { constraints ->
          val measurable = subcompose(1) {
            Xxx()
          }[0]
          val placeable = measurable.measure(constraints)
          val measurable2 = if (placeable.width > 1000) subcompose(2) {
            Image(painter = , contentDescription = )
          } else {
            Text(text =)
          }
          val placeable2 = measurable2.measure()
          layout(placeable.width, placeable.height) {
            placeable.placeRelative(0, 0)
          }
        }
        BoxWithConstraints() {
          constraints
          if (minWidth >= 360.dp) {
            Layout1()
          } else {
            Layout2()
          }
          // xxx_layout.xml  /layout-xxxx
          Text("扔物线", Modifier.align(Alignment.Center))
        }
      }
    }
  }
}