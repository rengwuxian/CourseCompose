package com.rengwuxian.composecourcedrag1d

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.core.view.GestureDetectorCompat
import com.rengwuxian.composecourcedrag1d.ui.theme.ComposeCourceDrag1DTheme
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
  
  @OptIn(ExperimentalFoundationApi::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      ComposeCourceDrag1DTheme {
        Modifier.scrollable(rememberScrollableState {
          println("又滚动了 $it 个像素")
          it
        }, Orientation.Horizontal, overscrollEffect = ScrollableDefaults.overscrollEffect())
        // 惯性滑动
        // 嵌套滑动
        // 滑动触边效果 overscroll
        SwipeToDismiss(state = , background = , dismissContent = )
      }
    }
  }
}

@Composable
private fun DraggableSample() {
  Column {
    val interactionSource = remember { MutableInteractionSource() }
    var offsetX by remember { mutableStateOf(0f) }
    Text("rengwuxian",
      Modifier
        .offset { IntOffset(offsetX.roundToInt(), 0) }
        .draggable(rememberDraggableState {
          println("又移动了 $it 个像素")
          offsetX += it
        }, Orientation.Horizontal, interactionSource = interactionSource)
    )
    val isDragged by interactionSource.collectIsDraggedAsState()
    Text(if (isDragged) "拖动中" else "静止")
  }
}