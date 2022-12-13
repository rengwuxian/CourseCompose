package com.rengwuxian.coursecomposemodifierlayout

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import com.rengwuxian.coursecomposemodifierlayout.ui.theme.CourseComposeModifierLayoutTheme
import kotlin.math.min

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CourseComposeModifierLayoutTheme {
        /*Box(Modifier.background(Color.Yellow)) {
          Text("rengwuxian", Modifier.layout { measurable, constraints ->
            val paddingPx = 10.dp.roundToPx()
            val placeable = measurable.measure(constraints.copy(
              maxWidth = constraints.maxWidth - paddingPx * 2,
              maxHeight = constraints.maxHeight - paddingPx * 2
            ))
            layout(placeable.width + paddingPx * 2, placeable.height + paddingPx * 2) {
              placeable.placeRelative(paddingPx, paddingPx)
            }
          })
        }*/
//        Modifier.requiredSize(80.dp)
        Modifier.padding().background()
        Modifier.background().padding()
        Box(Modifier.size(80.dp).size(40.dp).background(Color.Blue))
      }
    }
  }
}

class SquareImage(context: Context?, attrs: AttributeSet?) : ImageView(context, attrs) {
  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    val size = min(measuredWidth, measuredHeight)
    setMeasuredDimension(size, size)
  }

  override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
    super.onLayout(changed, left, top, right, bottom)
  }
}