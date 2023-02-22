package com.rengwuxian.coursecomposecustomtouch

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.calculatePan
import androidx.compose.foundation.gestures.calculateZoom
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import com.rengwuxian.coursecomposecustomtouch.ui.theme.CourseComposeCustomTouchTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CourseComposeCustomTouchTheme {
        Text("rengwuxian", Modifier.rengClick {
          println("发生事件：rengClick")
        })
      }
    }
  }
}

@Composable
private fun Modifier.rengClick(onClick: () -> Unit) = pointerInput(Unit) {
  awaitEachGesture {
    val down = awaitFirstDown()
    /*val event1 = awaitPointerEvent(PointerEventPass.Initial)
    val event2 = awaitPointerEvent(PointerEventPass.Main)
    val event3 = awaitPointerEvent(PointerEventPass.Final)
    event2.changes[0].isConsumed
    event2.changes[0].consume()
    event2.changes[0].isConsumed*/
    val event1 = awaitPointerEvent()
    val offset = event1.calculatePan()
    val scale = event1.calculateZoom()
    while (true) {
      val event = awaitPointerEvent()
      if (event.type == PointerEventType.Move) {
        val pos = event.changes[0].position
        if (pos.x < 0 || pos.x > size.width || pos.y < 0 || pos.y > size.height) {
          break
        }
      } else if (event.type == PointerEventType.Release && event.changes.size == 1) {
        onClick()
        break
      }
    }
  }
  detectTapGestures {  }
}

class CustomView(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {
  override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
    TODO("Not yet implemented")
  }

  override fun onTouchEvent(event: MotionEvent?): Boolean {
    MotionEvent.ACTION_DOWN
    MotionEvent.ACTION_POINTER_DOWN
    MotionEvent.ACTION_UP
    MotionEvent.ACTION_POINTER_UP
    MotionEvent.ACTION_MOVE
    return super.onTouchEvent(event)
  }

  override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
    return super.onInterceptTouchEvent(ev)
  }
}