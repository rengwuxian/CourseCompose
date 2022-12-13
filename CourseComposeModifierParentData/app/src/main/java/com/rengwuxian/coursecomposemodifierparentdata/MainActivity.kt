package com.rengwuxian.coursecomposemodifierparentdata

import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.rengwuxian.coursecomposemodifierparentdata.ui.theme.CourseComposeModifierParentDataTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CourseComposeModifierParentDataTheme {
        Row {
          Box(
            Modifier
              .size(40.dp)
              .background(Color.Blue)
              .weight(1f)) {
            Text(text = "123")
          }
          Box(
            Modifier
              .size(40.dp)
              .background(Color.Red)
              .weight(1f))
          Box(
            Modifier
              .size(40.dp)
              .background(Color.Green)
              .padding(20.dp)
              .layout { measurable, constraints ->

              })
          CustomLayout(Modifier.size(40.dp)) {
            Text("rengwuxian",
              Modifier
                .layoutId("big")
                .weightData(1f))
            Text("扔物线", Modifier.layoutId("small"))
            Box(
              Modifier
                .size(20.dp)
                .background(Color.Red))
          }
          CustomLayout2 {
            Text("1",
              Modifier
                .weightData(1f)
                .bigData(true))
            Text("2")
            Box {
              Text("3", Modifier.weightData(1f))
            }
          }
        }
      }
    }
  }
}

@Composable
fun CustomLayout(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
  Layout(content, modifier) { measurables, constraints ->
    measurables.forEach {
      when (it.layoutId) {
        "big" -> it.measure(constraints.xxxx)
        "small" -> it.measure(constraints.yyyy)
        else -> it.measure(constraints)
      }

    }
    layout(100, 100) {
      ...
    }
  }
}

@Composable
fun CustomLayout2(modifier: Modifier = Modifier, content: @Composable CustomLayout2Scope.() -> Unit) {
  Layout({ CustomLayout2Scope.content() }, modifier) { measurables, constraints ->
    measurables.forEach {
      val data = it.parentData as? Layout2Data
      val big = data?.big
      val weight = data?.weight
    }
    layout(100, 100) {
      ...
    }
  }
}

class Layout2Data(var weight: Float = 0f, var big: Boolean = false)

@LayoutScopeMarker
object CustomLayout2Scope {
  fun Modifier.weightData(weight: Float) = then(object : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?): Any? {
      return if (parentData == null) Layout2Data(weight)
      else (parentData as Layout2Data).apply { this.weight = weight }
    }
  })

  fun Modifier.bigData(big: Boolean) = then(object : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?): Any? {
      return ((parentData as? Layout2Data) ?: Layout2Data()).also {
        it.big = big
      }
    }
  })
}