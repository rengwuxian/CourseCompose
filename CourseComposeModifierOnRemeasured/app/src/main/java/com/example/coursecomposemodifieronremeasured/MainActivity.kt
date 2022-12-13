package com.example.coursecomposemodifieronremeasured

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.OnRemeasuredModifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.coursecomposemodifieronremeasured.ui.theme.CourseComposeModifierOnRemeasuredTheme

class MainActivity : ComponentActivity() {
  @OptIn(ExperimentalFoundationApi::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CourseComposeModifierOnRemeasuredTheme {
        Text("床前明月光", Modifier.then(object: OnRemeasuredModifier {
          override fun onRemeasured(size: IntSize) {
            TODO("Not yet implemented")
          }
        }))
        Modifier.padding(20.dp)
          .then(object : OnRemeasuredModifier {
            override fun onRemeasured(size: IntSize) {
              TODO("Not yet implemented")
            }
          })
          .padding(40.dp)
      }
    }
  }
}

class CustomView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    // dkfdkfjkdfjk
  }
}