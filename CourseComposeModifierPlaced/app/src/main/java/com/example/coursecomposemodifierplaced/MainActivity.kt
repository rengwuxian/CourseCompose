package com.example.coursecomposemodifierplaced

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.children
import com.example.coursecomposemodifierplaced.ui.theme.CourseComposeModifierPlacedTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CourseComposeModifierPlacedTheme {
        Text("床前明月光")
        OnPlacedModifier
        OnRemeasuredModifier // addAfterLayoutModifier()
        Modifier.onPlaced { layoutCoordinates ->
          val pos = layoutCoordinates.positionInParent()
          if (pos.x > ???)
        }.layout { measurable, constraints ->
          val placeable = measurable.measure()
          layout()
        }
      }
    }
  }
}

class CustomView(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {
  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
  }

  override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
    getChildAt(3).layout()
  }
}